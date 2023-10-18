package com.example.demo;

import com.example.demo.config.ReadingConfig;
import com.example.demo.service.ReadingService;
import com.example.demo.service.impl.ReadingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@EnableConfigurationProperties(ReadingConfig.class)
// 当存在reading.enable属性，且其值为true时，才初始化该Bean
@ConditionalOnProperty(name = "reading.enable", havingValue = "true")
public class ReadingConfiguration {

    @Autowired
    private ReadingConfig readingConfig;

    // 若当前IOC容器中没有Reading接口实现时，提供一个默认的Reading实现
    @Bean
    @ConditionalOnMissingBean(ReadingService.class)
    public ReadingService readingService() {
        return new ReadingServiceImpl(this.readingConfig);
    }
}
