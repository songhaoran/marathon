package com.song.bean.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class ArticleSourceEditReq {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @ApiModelProperty(value = "类别名称")
    private String name;

    @ApiModelProperty(value = "0-微信公众号;1-其他")
    private Integer sourceType;

    @ApiModelProperty(value = "爬取路径")
    private String snatchUrl;

    @ApiModelProperty(value = "每天的爬取时间,格式 hh:mm")
    private String snatchTime;

    @ApiModelProperty(value = "是否需要爬取：0-否；1-是")
    private Boolean isActive;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @ApiModelProperty(value = "上次抓取时间")
    private Date lastSnatchTime;

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createdAt;

}