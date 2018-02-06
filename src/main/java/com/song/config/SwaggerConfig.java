package com.song.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@ComponentScan("com.song")
@Slf4j
public class SwaggerConfig {

    @Autowired
    ConfigurableEnvironment environment;

    @Value("")
    String buildTimestamp;
    @Value("")
    String version;
    @Value("")
    String wikiApiProtocolUrl;

    String PROJECT_NAME = "接口文档";

    @Bean
    public Docket swaggerSpringfoxDocket() {
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("API")
                .consumes(Collections.singleton(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .produces(Collections.singleton(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .paths(PathSelectors.regex(".*")) // and by paths
                .build().securitySchemes(apiKeys())
                .securityContexts(Lists.newArrayList(securityContext()));
        return swaggerSpringMvcPlugin;
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("基础平台 RESTful APIs")
                .description("基础平台 RESTful 风格的接口文档，内容详细，极大的减少了前后端的沟通成本，同时确保代码与文档保持高度一致，极大的减少维护文档的时间。")
                .termsOfServiceUrl("http://songhaoran.cn")
                .version("1.0.0")
                .build();
    }

    @Autowired
    private TypeResolver typeResolver;

    private List<ApiKey> apiKeys() {
        List<ApiKey> list = Lists.newArrayList();
        //list.add(new ApiKey("mykey2", "api_key", "header"));
        return list;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*?"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Lists.newArrayList(new SecurityReference("mykey", authorizationScopes));
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "test-app-client-id",
                "test-app-client-secret",
                "test-app-realm",
                "test-app",
                "",
                ApiKeyVehicle.HEADER,
                "api_key",
                "," );
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration("", "none", "alpha", "schema",
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, true, true);
    }
}
