package com.example.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@EnableConfigurationProperties(PersonConfig.class)
@SpringBootApplication
public class Application {
	@Bean
	@ConfigurationProperties(prefix = "datasource")
	public DruidDataSource dataSource(){
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

		PersonConfig personConfig = ctx.getBean(PersonConfig.class);
		System.out.println(personConfig);

		DruidDataSource dataSource = ctx.getBean(DruidDataSource.class);
		String driverClassName = dataSource.getDriverClassName();
		System.out.println(driverClassName);
	}
}
