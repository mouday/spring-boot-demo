package com.example.demo.config;


import com.example.demo.annotation.ApiRestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置统一的后台接口访问路径的前缀
 */
// @Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
                .addPathPrefix("/api/app", c -> c.isAnnotationPresent(ApiRestController.class));
    }

}