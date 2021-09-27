# Spring-Boot-Hello-World

下载项目模板：https://start.spring.io/

用最少的代码运行起来一个SpringBoot项目

```java
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 首页返回字符串
     */
    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World";
    }
}
```

运行项目
```bash
$ mvn spring-boot:run
```