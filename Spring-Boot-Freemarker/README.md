# SpringBoot整合模板引擎Freemarker

项目结构
```
.
├── README.md
├── pom.xml 
└── src
    ├── main
    │   ├── java
    │   │    └── com
    │   │        └── example
    │   │               ├── Application.java
    │   │               └── controller
    │   │                   └── FreemarkerController.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       │   └── main.js
    │       └── templates
    │           └── index.ftl

```
依赖 pom.xml
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

配置 application.properties
```bash
# 关闭缓存
spring.freemarker.cache=false
spring.freemarker.suffix=.ftl
spring.freemarker.template-loader-path=classpath:/templates
spring.freemarker.content-type=text/html; charset=utf-8

# 静态文件
spring.mvc.static-path-pattern=/static/**
```

控制器 FreemarkerController.java

```java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FreemarkerController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Tom");
        return "index";
    }
}
```

模板文件 templates/index.ftl

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Demo</title>
    <script src="/static/main.js"></script>
</head>
<body>
    Hello ${name}
</body>
</html>
```

>参考
[springboot 集成 freemarker](https://www.cnblogs.com/zhang-dongliang/p/10970588.html)
