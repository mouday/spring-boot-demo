package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


// 自动扫描mapper接口，不用每个mapper都添加@Mapper注解
@MapperScan(value = {"com.example.demo.mapper"})
@SpringBootApplication
@EnableCaching
@EnableRabbit
@EnableAsync // 开启异步任务
@EnableScheduling // 开启定时任务
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
