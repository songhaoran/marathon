package com.song.util.enums;

/**
 * Created by Song on 2018/03/16.
 */
public enum ArticleSnatchTypeEnum {
    wx_offical_account(0, "微信公总号");

    private Integer type;
    private String desc;

    ArticleSnatchTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
