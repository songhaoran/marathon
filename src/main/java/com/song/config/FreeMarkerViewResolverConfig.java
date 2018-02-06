package com.song.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Created by Song on 2017/6/7.
 * 主要用于设置ftl页面中的${base}路径
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "freemarker")
public class FreeMarkerViewResolverConfig {
    private String viewClass;
    private Boolean cache;
    private Boolean exposeSpringMacroHelpers;
    private Boolean exposeRequestAttributes;
    private Boolean exposeSessionAttributes;
    private String requestContextAttribute;
    private String contentType;
    private Integer order ;


    @Bean
    public FreeMarkerViewResolver getFreeMarkerViewResolver() throws Exception {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setViewClass((Class<FreeMarkerView>) Class.forName(viewClass));
        freeMarkerViewResolver.setCache(cache);
        freeMarkerViewResolver.setExposeSpringMacroHelpers(exposeSpringMacroHelpers);
        freeMarkerViewResolver.setExposeRequestAttributes(exposeRequestAttributes);
        freeMarkerViewResolver.setExposeSessionAttributes(exposeSessionAttributes);
        freeMarkerViewResolver.setRequestContextAttribute(requestContextAttribute);
        freeMarkerViewResolver.setContentType(contentType);
        freeMarkerViewResolver.setOrder(order);
        return freeMarkerViewResolver;
    }
}
