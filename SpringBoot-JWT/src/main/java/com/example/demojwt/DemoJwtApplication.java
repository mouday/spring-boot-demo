package com.example.demojwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DemoJwtApplication {

	public static void main(String[] args) {
		// 打印当前运行的端口
		ConfigurableApplicationContext context = SpringApplication.run(DemoJwtApplication.class, args);

		Environment environment = context.getBean(Environment.class);

		System.out.println("访问链接：http://localhost:" + environment.getProperty("local.server.port"));
	}

}
