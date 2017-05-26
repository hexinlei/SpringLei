package com.ant.ranger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @packgeName: com.ant.ranger.config
 * @ClassName: WebConfig
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/19-下午6:48
 * @version: 1.0
 * @since: JDK 1.8
 */
@Configuration
@EnableWebMvc
public class WebLoginConfig extends WebMvcConfigurerAdapter{

    @Value("${authHeader}")
    private String tokenHeader;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        super.addCorsMappings(registry);
        registry.addMapping("/login");

        registry.addMapping("/ajax/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
                .allowedHeaders(this.tokenHeader, "Content-Type")
                .exposedHeaders(this.tokenHeader)
                .allowCredentials(true)
                .maxAge(3600);
    }
}
