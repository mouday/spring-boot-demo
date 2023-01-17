package com.example.demo.config;

import com.example.demo.annotation.Development;
import com.example.demo.entity.AppData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Profile("development")
public class DevelopmentConfig {
    @Bean("appData")
    public AppData getAppData() {
        AppData appData = new AppData();
        appData.setEnvironmentName("development");

        return appData;
    }
}
