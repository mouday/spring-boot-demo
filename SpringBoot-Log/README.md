# Spring-Boot-Log

日志输出

```java
package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Application {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 首页返回字符串
     */
    @GetMapping("/")
    @ResponseBody
    public String index() {
        logger.info("index");
        return "Hello World";
    }

}

```

调试的时候日志太长，看不全，可以只输出关键信息

```bash
# 控制台输出日志格式
logging.pattern.console=%msg%n
```

运行项目

```bash
$ mvn spring-boot:run
```

logging 配置参数：
https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.core
