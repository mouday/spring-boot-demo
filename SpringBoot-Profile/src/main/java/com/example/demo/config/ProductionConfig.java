package com.example.demo.config;

import com.example.demo.annotation.Production;
import com.example.demo.entity.AppData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Profile("production")
public class ProductionConfig {

    @Bean("appData")
    public AppData getAppData() {
        AppData appData = new AppData();
        appData.setEnvironmentName("production");

        return appData;
    }
}
