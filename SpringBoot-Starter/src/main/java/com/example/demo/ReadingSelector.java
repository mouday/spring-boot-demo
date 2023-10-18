package com.example.demo;
import com.example.demo.config.ReadingConfig;
import com.example.demo.service.ReadingService;
import com.example.demo.service.impl.ReadingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ReadingConfig.class)
public class ReadingSelector {

    @Autowired
    private ReadingConfig readingConfig;

    // 当SpringIOC容器中不存在Reading实现时，才往Spring中初始化ReadingService作为Reading接口的实现
    @Bean
    @ConditionalOnMissingBean(ReadingService.class)
    public ReadingService readingService() {
        return new ReadingServiceImpl(this.readingConfig);
    }

}
