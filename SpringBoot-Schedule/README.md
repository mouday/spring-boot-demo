# SpringBoot 定时任务

```java
package com.example.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * 定时任务
 * https://blog.csdn.net/enthan809882/article/details/103742811
 */
@Configuration
@EnableScheduling
public class ScheduleConfiguration {

    /**
     * 间隔执行，启动的时候就会执行一次
     */
    @Scheduled(fixedDelay = 1000 * 5) //  5秒
    public void fixedTask() {
        System.out.println("fixedTask: " + LocalDateTime.now());
        // 执行日志
        // fixedTask: 2023-01-16T18:07:12.526
        // fixedTask: 2023-01-16T18:07:17.531
        // fixedTask: 2023-01-16T18:07:22.533
        // fixedTask: 2023-01-16T18:07:27.535
    }


    /**
     * 定时执行 秒 分 时 日 月 周
     */
    @Scheduled(cron = "*/5 * * * * *")  // 间隔5秒
    public void cronTask() {
        System.out.println("cronTask: " + LocalDateTime.now());

        // 执行日志
        // cronTask: 2023-01-16T18:06:15.005
        // cronTask: 2023-01-16T18:06:20.004
        // cronTask: 2023-01-16T18:06:25.005
        // cronTask: 2023-01-16T18:06:30.005
    }
}

```