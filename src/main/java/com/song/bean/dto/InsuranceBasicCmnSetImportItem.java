package com.song.bean.dto;

import com.song.util.annotation.ImportField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Song on 2019/10/16.
 */
@Data
public class InsuranceBasicCmnSetImportItem {

    @ImportField(column = 0)
    private String companyName;

    @ImportField(column = 1)
    private String productName;

    @ImportField(column = 2)
    private String contributionPeriod;

    @ImportField(column = 3)
    private String currency;

    @ImportField(column = 4)
    private String payMethod;

    @ImportField(column = 5)
    private Integer policyholdersMinAge;

    @ImportField(column = 6)
    private Integer policyholdersMaxAge;

    @ImportField(column = 8)
    private String theFirstYear;

    @ImportField(column = 9)
    private String theSecondYear;

    @ImportField(column = 10)
    private String theThirdYear;

    @ImportField(column = 11)
    private String theFourthYear;

    @ImportField(column = 12)
    private String theFifthYear;

    @ImportField(column = 13)
    private String theSixthYear;

    @ImportField(column = 14)
    private String theSeventhYear;

    @ImportField(column = 15)
    private String theEighthYear;

    @ImportField(column = 16)
    private String theNinthYear;

    @ImportField(column = 17)
    private String theTenthYear;

    @ImportField(column = 18)
    private Date effectiveStartDate;

    @ImportField(column = 19, datePattern = ImportField.DATE_PATTERN_2)
    private Date effectiveEndDate;
}
