Java：SpringBoot @Async实现异步任务

@[TOC](目录)

依赖pom.xml

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
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
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

## 1、同步任务

应用启动类

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

同步任务，耗时2秒

```java
package com.example.demo.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
public class TaskService {
    /**
     * 同步任务
     * @throws InterruptedException
     */
    public void syncTask() throws InterruptedException {
        Thread.sleep(1000 * 2);
        System.out.println("syncTask");
    }
}

```
控制器

```java
package com.example.demo.controller;

import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/syncTask")
    public String syncTask() throws InterruptedException {
        long start = System.currentTimeMillis();

        taskService.syncTask();

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return "success";
    }
}

```

请求接口：等待2秒后返回

```
GET http://localhost:8080/task/syncTask
Accept: application/json

```

## 2、@Async 异步任务-无返回值

启动类需要加上注解：`@EnableAsync`

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

```

异步任务

```java

package com.example.demo.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
public class TaskService {
    /**
     * 异步任务
     * @throws InterruptedException
     */
    @Async
    public void asyncTask() throws InterruptedException {
        Thread.sleep(1000 * 2);
        System.out.println("asyncTask");
    }
}

```

控制器
```java
package com.example.demo.controller;

import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/asyncTask")
    public String asyncTask() throws InterruptedException {
        long start = System.currentTimeMillis();

        taskService.asyncTask();

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return "success";

    }
}

```

请求接口：无等待，直接返回 

```
GET http://localhost:8080/task/asyncTask
Accept: application/json

```

## 3、@Async 异步任务-有返回值

异步任务

```java

package com.example.demo.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
public class TaskService {
   /**
    * 异步任务, 有返回值
    * @throws InterruptedException
    */
   @Async
   public Future<String> asyncTaskFuture() throws InterruptedException {
       Thread.sleep(1000 * 2);
       System.out.println("asyncTaskFuture");

       return new AsyncResult<>("asyncTaskFuture");
   }
}

```

控制器

```java
package com.example.demo.controller;

import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/asyncTaskFuture")
    public String asyncTaskFuture() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        Future<String> future = taskService.asyncTaskFuture();

        // 线程等待结果返回
        String result = future.get();

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return result;
    }
}

```

请求接口：等待2秒后返回 

```
GET http://localhost:8080/task/asyncTaskFuture
Accept: application/json

```

## 4、@Async + 自定义线程池

```java
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池
 */
@Configuration
public class ExecutorAsyncConfig {

    @Bean(name = "newAsyncExecutor")
    public Executor newAsync() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        // 设置核心线程数
        taskExecutor.setCorePoolSize(10);
        // 线程池维护线程的最大数量，只有在缓冲队列满了以后才会申请超过核心线程数的线程
        taskExecutor.setMaxPoolSize(100);
        // 缓存队列
        taskExecutor.setQueueCapacity(50);
        // 允许的空闲时间，当超过了核心线程数之外的线程在空闲时间到达之后会被销毁
        taskExecutor.setKeepAliveSeconds(200);
        // 异步方法内部线程名称
        taskExecutor.setThreadNamePrefix("my-xiaoxiao-AsyncExecutor-");
        // 拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();

        return taskExecutor;
    }
}
```

异步任务

```java

package com.example.demo.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
public class TaskService {
   /**
    * 自定义线程池 执行异步任务
    * @throws InterruptedException
    */
   @Async("newAsyncExecutor")
   public void asyncTaskNewAsync() throws InterruptedException {
       Thread.sleep(1000 * 2);

       System.out.println("asyncTaskNewAsync");

   }
}

```

控制器

```java
package com.example.demo.controller;

import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/asyncTaskNewAsync")
    public String asyncTaskNewAsync() throws InterruptedException {
        long start = System.currentTimeMillis();

        taskService.asyncTaskNewAsync();

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return "success";
    }
}

```

请求接口：无等待，直接返回 

```
GET http://localhost:8080/task/asyncTaskNewAsync
Accept: application/json

```

## 5、CompletableFuture 实现异步任务

不需要在启动类上加`@EnableAsync` 注解，也不需要在方法上加`@Async` 注解

控制器

```java
package com.example.demo.controller;

import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    @GetMapping("/completableFuture")
    public String completableFuture(){
        long start = System.currentTimeMillis();

        // CompletableFuture实现异步任务
        CompletableFuture<Void> result = CompletableFuture.runAsync(() -> {

            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("CompletableFuture");
        });

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return "success";
    }

}

```
>参考
>[SpringBoot使用@Async的总结！](https://mp.weixin.qq.com/s/xwTMXuHEpgFipiHrOuYgNA)