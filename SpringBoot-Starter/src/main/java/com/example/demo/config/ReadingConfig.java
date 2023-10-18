package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取application.properties中reading相关的配置
 */
@Data
@ConfigurationProperties(prefix = "reading")
public class ReadingConfig {
    /**
     * 启用开关
     */
    private Boolean enable = false;

    /**
     * 类型
     */
    private String type = "text";
}
