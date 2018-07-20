package com.song.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Song on 2018/2/7.
 */
@Data
@Configuration
public class ConfigParam {
    @Value("${jpush.apns_production}")
    Boolean apns_production;
}
