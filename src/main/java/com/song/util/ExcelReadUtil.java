package com.song.util;

import com.song.exception.MeixinException;
import com.song.util.annotation.ImportField;
import com.song.util.enums.BooleanType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Song on 2019/07/26.
 */
@Slf4j
public class ExcelReadUtil<T> {

    /**
     * 读取excel
     *
     * @param file        excel文件
     * @param startRow    起始行(第一行为0)
     * @param startColumn 起始列(第一列为0)
     * @param endColumn   结束列
     * @param clazz       映射类class
     * @return
     * @throws Exception
     */
    public List<T> readExcel(File file, Integer startRow, Integer startColumn, Integer endColumn, Class clazz) throws Exception {
        List<String[]> excelValues = this.getExcelValues(file, startRow, startColumn, endColumn);
        List<T> list = this.convert(excelValues, clazz);
        return list;
    }

    private List<String[]> getExcelValues(File file, Integer startRow, Integer startColumn, Integer endColumn) {
//        FileInputStream fileInputStream;
//        try {
//            fileInputStream = (FileInputStream) file.getInputStream();
//        } catch (IOException e) {
//            throw new MeixinException("文件读取失败");
//        }

        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (Exception e) {
            throw new MeixinException("文件读取失败");
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            throw new MeixinException("文件读取失败");
        }

        List<String[]> values = new ArrayList<>();
        int columnCount = endColumn - startColumn + 1;
        int rowNum = startRow;
        while (sheet.getRow(rowNum) != null) {
            XSSFRow row = sheet.getRow(rowNum);
            if (isRowEmpty(row)) {
                break;
            }

            String[] arr = new String[columnCount];
            int j = 0;
            int columnNum = startColumn;
            while (columnNum <= endColumn) {
                XSSFCell cell = row.getCell(columnNum++);
                String value = "";
                if (cell != null) {
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case _NONE:
                            value = "";
                            break;
                        case NUMERIC:
                            value = String.valueOf(cell.getNumericCellValue());
                            break;
                        case STRING:
                            value = cell.getStringCellValue();
                            break;
                        case FORMULA:
                            value = cell.getCellFormula();
                            break;
                        case BLANK:
                            value = "";
                            break;
                        case BOOLEAN:
                            value = cell.getBooleanCellValue() ? "TRUE" : "FALSE";
                            break;
                        default:
                            value = "";
                            break;
                    }
                }
                arr[j++] = value;
            }

            values.add(arr);
            rowNum++;
        }

        return values;
    }

    private boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private List<T> convert(List<String[]> excelValueList, Class clazz) throws Exception {
        if (excelValueList == null || excelValueList.size() == 0) {
            return new ArrayList<>();
        }

        Map<Integer, List<Field>> column2FieldMap = getRow2FieldMap(clazz);
        List<T> list = new ArrayList<>();
        for (String[] values : excelValueList) {
            T t = (T) clazz.newInstance();
            for (Map.Entry<Integer, List<Field>> entry : column2FieldMap.entrySet()) {
                Integer column = entry.getKey();
                List<Field> fieldList = entry.getValue();

                String cellVale = values[column];
                if (StringUtils.isBlank(cellVale)) {
                    continue;
                }
                for (Field field : fieldList) {
                    Class<?> fieldType = field.getType();
                    ImportField annotation = field.getAnnotation(ImportField.class);
                    String fieldName = field.getName();
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = clazz.getMethod(methodName, fieldType);
                    try {
                        if (fieldType.equals(String.class)) {
                            method.invoke(t, cellVale);
                        } else if (fieldType.equals(Integer.class)) {
                            method.invoke(t, Integer.parseInt(cellVale));
                        } else if (fieldType.equals(Date.class)) {
                            SimpleDateFormat format = new SimpleDateFormat(annotation.datePattern());
                            method.invoke(t, format.parse(cellVale));
                        } else if (fieldType.equals(Boolean.class)) {
                            BooleanType booleanType = annotation.booleanType();
                            if (booleanType.getTrueStr().equals(cellVale)) {
                                method.invoke(t, Boolean.TRUE);
                            } else if (booleanType.getFalseStr().equals(cellVale)) {
                                method.invoke(t, Boolean.FALSE);
                            } else {
                                throw new MeixinException("布尔类型值转换失败");
                            }
                        } else if (fieldType.equals(BigDecimal.class)) {
                            method.invoke(t, new BigDecimal(cellVale));
                        }
                    } catch (Exception e) {
                        log.error("值设置失败!row={},column={},value={}", excelValueList.indexOf(values), column, cellVale, e);
                    }
                }
            }
            list.add(t);
        }
        return list;
    }

    public static Map<Integer, List<Field>> getRow2FieldMap(Class clazz) {
        Map<Integer, List<Field>> rowNum2FieldMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ImportField annotation = field.getAnnotation(ImportField.class);
            if (annotation == null) {
                throw new MeixinException("映射类字段未配置响应枚举");
            }
            int rowNum = annotation.column();
            List<Field> fieldList = rowNum2FieldMap.get(rowNum);
            if (fieldList == null) {
                fieldList = new ArrayList<>();
                rowNum2FieldMap.put(rowNum, fieldList);
            }
            fieldList.add(field);
        }
        return rowNum2FieldMap;
    }
}
