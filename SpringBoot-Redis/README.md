# SpringBoot Redis

目录结构

```
$ tree
.
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── demo
    │   │               ├── Application.java
    │   │               ├── config
    │   │               │   └── RedisConfig.java
    │   │               ├── controller
    │   │               │   └── UserController.java
    │   │               ├── service
    │   │               │   ├── UserService.java
    │   │               │   └── impl
    │   │               │       └── UserServiceImpl.java
    │   │               └── utils
    │   │                   └── RedisUtils.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        ├── http
        │   └── api.http
        └── java
            └── com
                └── example
                    └── demo
                        └── ApplicationTests.java
```

redis依赖

```xml
<!-- redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

完整pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.7</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
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
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.68</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

配置文件 application.properties

```bash
# 连接的那个数据库
spring.redis.database=0
# redis服务的ip地址
spring.redis.host=127.0.0.1
# redis端口号
spring.redis.port=6379
# redis的密码，没设置过密码，可为空
spring.redis.password=

# 时间格式化
# https://www.jianshu.com/p/044e2155870a
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8
```

配置 RedisConfig.java
```java
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类
 */
@Configuration
public class RedisConfig {
    @Bean // redisTemplate注入到Spring容器
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 序列化配置
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        RedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        // key序列化
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // value序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }
}
```
工具类 RedisUtils.java
```java
package com.example.demo.utils;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * 参考 https://blog.csdn.net/qq_49067108/article/details/119826547
 */
@Service
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    // key前缀，区分不同应用
    public static final String Prefix = "spring-boot-redis";

    /**
     * 获取key
     *
     * @param key
     * @return
     */
    private String getKey(String key) {
        return Prefix + ":" + key;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public void set(final String key, Object value) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(this.getKey(key), JSON.toJSONString(value));
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public void set(final String key, Object value, Long expireTime) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(this.getKey(key), JSON.toJSONString(value), expireTime, TimeUnit.SECONDS);
    }

    /**
     * 批量删除对应的value
     *
     * @param key
     */
    public void delete(final String key) {
        redisTemplate.delete(this.getKey(key));
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean hasKey(final String key) {
        return redisTemplate.hasKey(this.getKey(key));
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public <T> T get(final String key, Class<T> clazz) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String value = operations.get(this.getKey(key));

        return JSON.parseObject(value, clazz);
    }
}
```

启动类 Application.java

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```

接口类 UserService.java
```java
package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    public User getUser();
}

```

实现类 UserServiceImpl.java

```java
package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        log.info("DataServiceImpl getUser");

        // 模拟耗时操作
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName("Tom");
        user.setAge(20);
        user.setBirthday(new Date());

        return user;
    }
}

```

控制器 RedisController.java

```java
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser() {
        String key = "data";

        User user = redisUtils.get(key, User.class);

        log.info("RedisController getUser: {}", user);

        if (user == null) {
            // 数据不存在，获取数据
            user = userService.getUser();
            redisUtils.set(key, user);
        }

        return user;
    }

}

```

请求接口测试
```
GET http://localhost:8080/getUser
Accept: application/json
```

>参考
[SpringBoot实战：整合Redis、mybatis，封装RedisUtils工具类等（附源码）](https://mp.weixin.qq.com/s/GzjNktI9Fy_AA63ZXLg-pQ)

