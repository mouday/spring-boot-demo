package com.mouday.paymentdemo.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.mouday.paymentdemo.mapper")
// 启用事务管理
@EnableTransactionManagement
public class MyBatisPlusConfig {

}
