package com.song.common;

import com.alibaba.fastjson.JSON;
import com.song.util.annotation.ImportField;
import com.song.bean.dto.ImportOrderItem;
import com.song.util.ExcelReadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Song on 2019/07/26.
 */
@Slf4j
public class ImportFieldTest {

    @Test
    public void fieldTest() {
        ImportOrderItem item = new ImportOrderItem();
        Class clazz = item.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ImportField annotation = field.getAnnotation(ImportField.class);
            System.out.println("annotation:" + JSON.toJSONString(annotation));
            System.out.println(field.getType().equals(Integer.class));
        }
    }

    @Test
    public void testRead() throws Exception {
        File file = new File("/Users/songwenhao/Downloads/导入测试.xlsx");
        ExcelReadUtil<ImportOrderItem> excelReadUtil = new ExcelReadUtil<>();
        List<ImportOrderItem> list = excelReadUtil.readExcel(file, 1, 0, 10, ImportOrderItem.class);
        System.out.println();
    }


}
