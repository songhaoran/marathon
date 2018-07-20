package com.song.query;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ArticleSourceQuery {
    @ApiModelProperty(value = "0-微信公众号;1-其他")
    private Integer sourceType;

    @ApiModelProperty(value = "是否需要爬取：0-否；1-是")
    private Boolean isActive;
}