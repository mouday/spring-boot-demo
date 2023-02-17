package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private FileStorageConfig fileStorageConfig;

    // 设置静态资源映射
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 上传文件
        String basePath = fileStorageConfig.getBasePath();
        String baseUrl = fileStorageConfig.getBaseUrl();

        String pathPattern = baseUrl + "/**";
        String location = "file:" + basePath + "/";

        // 上传文件
        registry.addResourceHandler(pathPattern)
                .addResourceLocations(location)
                .setCacheControl(CacheControl.noCache());

        // 静态资源
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache());
    }
}
