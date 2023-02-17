package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;


@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "spring.file-storage")
public class FileStorageConfig {

    private String domain;

    private String basePath;

    private String baseUrl;

    /**
     * 获取文件保存路径
     * 参考：https://www.bbsmax.com/A/GBJrE67Wz0/
     *
     * @return File
     */
    public String getUploadDirectory() {
        // 获取当前运行目录
        File path = new File("");

        System.out.println("path:" + path.getAbsolutePath());

        File uploadDir = new File(path.getAbsolutePath(), this.getBasePath());

        // 不存在则创建
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        return uploadDir.getAbsolutePath();
    }
}
