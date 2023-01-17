package com.example.demo.config;

import com.example.demo.annotation.Development;
import com.example.demo.annotation.Production;
import com.example.demo.entity.AppData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 开发环境
    @Bean("appConfigData")
    @Development
    public AppData getDevelopmentAppData() {
        AppData appData = new AppData();
        appData.setEnvironmentName("app development");

        return appData;
    }

    // 正式环境
    @Bean("appConfigData")
    @Production
    public AppData getProductionAppData() {
        AppData appData = new AppData();
        appData.setEnvironmentName("app production");

        return appData;
    }
}
