package com.song.bean.dto;

import com.song.util.enums.BooleanType;
import com.song.util.annotation.ImportField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Song on 2019/07/26.
 */
@Data
public class ImportOrderItem {
    @ImportField(column = 0)
    private Integer advisorId;

    @ImportField(column = 1)
    private Integer productId;

    @ImportField(column = 2)
    private Integer investorId;

    @ImportField(column = 3)
    private BigDecimal investAmount;

    @ImportField(column = 4)
    private String investorPhone;

    @ImportField(column = 5, booleanType = BooleanType.Chinese)
    private Boolean isReceived;

    @ImportField(column = 6, datePattern = ImportField.DATE_PATTERN_1)
    private Date investDate;
}
