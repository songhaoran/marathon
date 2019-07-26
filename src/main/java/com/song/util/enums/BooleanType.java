package com.song.util.enums;

/**
 * Created by Song on 2019/07/26.
 */
public enum BooleanType {
    true_false("TRUE","FALSE"),
    yes_no("YES","NO"),
    Chinese("是","否"),
    number("1","0");

    String trueStr;
    String falseStr;

    BooleanType(String trueStr, String falseStr) {
        this.trueStr = trueStr;
        this.falseStr = falseStr;
    }

    public String getTrueStr() {
        return trueStr;
    }

    public String getFalseStr() {
        return falseStr;
    }
}
