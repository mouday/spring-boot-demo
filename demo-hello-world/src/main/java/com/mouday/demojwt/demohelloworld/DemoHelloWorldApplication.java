package com.mouday.demojwt.demohelloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class DemoHelloWorldApplication {

	public static void main(String[] args) {
		// 打印当前运行的端口
		ConfigurableApplicationContext context = SpringApplication.run(DemoHelloWorldApplication.class, args);

		Environment environment = context.getBean(Environment.class);

		System.out.println("访问链接：http://localhost:" + environment.getProperty("local.server.port"));
	}

	/**
	 * 首页返回字符串
	 */
	@GetMapping("/")
	@ResponseBody
	public String index(){
		return "Hello World";
	}

}
