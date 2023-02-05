# SpringBoot Pebble

Pebble模板引擎，和PHP中的Twig、Python中的Django/jinja2模板语法类似

文档
- [https://pebbletemplates.io/](https://pebbletemplates.io/)
- [Pebble Spring Boot Starter](https://pebbletemplates.io/wiki/guide/spring-boot-integration/)
- [https://github.com/PebbleTemplates/pebble](https://github.com/PebbleTemplates/pebble)
- [https://mvnrepository.com/artifact/io.pebbletemplates/pebble-spring-boot-starter](https://mvnrepository.com/artifact/io.pebbletemplates/pebble-spring-boot-starter)

依赖

```xml
<dependency>
    <groupId>io.pebbletemplates</groupId>
    <artifactId>pebble-spring-boot-starter</artifactId>
    <version>3.1.6</version>
</dependency>
```

> 注意

官方文档给出的版本号对应

| ArtifactId	| spring-boot version | 
| - | - |  
| pebble-legacy-spring-boot-starter	| 2.x.x
| pebble-spring-boot-starter	| 3.x.x

可是，使用`pebble-legacy-spring-boot-starter` 压根就不好使

打开start的依赖配置可以看到

pebble-spring-boot-starter-3.1.6

```xml
<properties>
    <boot.version>2.7.3</boot.version>
</properties>
```

pebble-spring-boot-starter-3.2.0

```xml
<properties>
    <java.version>17</java.version>
    <boot.version>3.0.0</boot.version>
</properties>
```

所以，spring-boot-2版本，还得使用`pebble-spring-boot-starter`,只是不用最新版

完整配置pom.xml

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
			<groupId>io.pebbletemplates</groupId>
			<artifactId>pebble-spring-boot-starter</artifactId>
			<version>3.1.6</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
	</dependencies>

</project>

```

配置 application.properties
```yaml
# 文件扩展名，默认 .pebble
pebble.suffix=.html
```

控制器

```java
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Tom");
        return "index";
    }
}
```

模板文件 resources/templates/index.html

```html
<body>
    <h2>hello {{name}}!</h2>
</body>

```