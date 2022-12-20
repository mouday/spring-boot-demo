# Spring-Boot-Hello-World

下载项目模板：https://start.spring.io/


用最少的代码运行起来一个SpringBoot项目

依赖
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.5</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<groupId>com.example</groupId>
<artifactId>Spring-Boot-Hello-World</artifactId>
<version>0.0.1-SNAPSHOT</version>

<name>Spring-Boot-Hello-World</name>
<description>Demo project for Spring Boot</description>

<properties>
    <java.version>1.8</java.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <version>2.4.0</version>
        <optional>true</optional>
    </dependency>

</dependencies>
```

代码示例

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