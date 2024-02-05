package com.example.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * 定时任务
 * https://blog.csdn.net/enthan809882/article/details/103742811
 */
@Configuration
@EnableScheduling
public class ScheduleConfiguration {

    /**
     * 延时执行，启动的时候就会执行一次
     * 上一次执行完成后，延迟固定时间再执行下一次任务
     * 任务执行完间隔5秒继续下一次执行
     * 例如：每隔5秒执行一次，任务耗时10秒
     * 如果0秒的时候执行了任务，那么，下一次执行时间应该是15秒
     */
    //@Scheduled(fixedDelay = 1000 * 2) //  10秒
    public void fixedDelayTask() {
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fixedDelayTask: " + LocalDateTime.now());
        // 执行日志
        //fixedTask: 2024-02-05T13:32:54.054
        //fixedTask: 2024-02-05T13:33:09.064
        //fixedTask: 2024-02-05T13:33:24.071
        //fixedTask: 2024-02-05T13:33:39.075
        //fixedTask: 2024-02-05T13:33:54.080
    }

    /**
     * 固定间隔执行，和执行时间无关
     */
    @Scheduled(fixedRate = 1000 * 2)
    public void fixedRateTask1() {
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("fixedRateTask1: " + Thread.currentThread().getId() + " - " + LocalDateTime.now());
    }

    @Scheduled(fixedRate = 1000 * 3)
    public void fixedRateTask2() {
        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("fixedRateTask2: " + Thread.currentThread().getId() + " - " + LocalDateTime.now());
    }


    /**
     * 定时执行 秒 分 时 日 月 周
     */
    //@Scheduled(cron = "*/5 * * * * *")  // 间隔10秒
    public void cronTask() {
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cronTask: " + LocalDateTime.now());

        // 执行日志
        //cronTask: 2024-02-05T13:37:15.021
        //cronTask: 2024-02-05T13:37:25.004
        //cronTask: 2024-02-05T13:37:35.002
        //cronTask: 2024-02-05T13:37:45.006
        //cronTask: 2024-02-05T13:37:55.004
    }
}
