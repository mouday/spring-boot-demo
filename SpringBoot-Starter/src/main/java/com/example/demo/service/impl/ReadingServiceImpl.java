package com.example.demo.service.impl;

import com.example.demo.config.ReadingConfig;
import com.example.demo.service.ReadingService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadingServiceImpl implements ReadingService {
    /**
     * reading相关配置类
     */
    private ReadingConfig readingConfig;

    public ReadingServiceImpl(ReadingConfig readingConfig) {
        this.readingConfig = readingConfig;
    }

    @Override
    public void reading() {
        log.info("reading type: {}", this.readingConfig.getType());
    }
}
