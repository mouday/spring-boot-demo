# SpringBoot整合Mail发送邮件

配置

```bash
# 配置邮箱参数
spring.mail.host=smtp.163.com
spring.mail.username=xxxx@163.com
# 户端授权码
spring.mail.password=******
spring.mail.default-encoding=UTF-8
```

发送邮件

```java
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    @Value("${spring.mail.username}")
    private String fromUser;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送Html邮件
     * @param toUser  接收人
     * @param subject 主题
     * @param content 内容
     * @throws MessagingException
     */
    public void sendHtmlMail(
            String toUser,
            String subject,
            String content) throws MessagingException {

        MimeMessage message = this.mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(this.fromUser);
        helper.setTo(toUser);

        helper.setSubject(subject);
        helper.setText(content, true);

        this.mailSender.send(message);

    }
}

```

## 使用Thymeleaf作为html模板：

依赖

```xml
<!-- 邮件 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<!-- 模板引擎 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```


```java
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class ThymeleafService {
    @Autowired
    TemplateEngine templateEngine;

    public String renderTemplate(String templateName, Map<String, Object> model){
        Context content = new Context();

        for (String key : model.keySet()) {
            content.setVariable(key, model.get(key));
        }

        return templateEngine.process(templateName, content);
    }
}

```

templates/thymeleaf-mail.html
```html

<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <div>
        Hi, My name is <span th:text="${name}"></span>,
        I'm <span th:text="${age}"></span> years old.
    </div>
</html>
```

>参考
>[Java笔记：SpringBoot发送邮件](https://pengshiyu.blog.csdn.net/article/details/109569290)

## 使用FreeMarker作为html模板：

依赖

```xml
<!-- 邮件 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<!-- 模板引擎 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

```java
package com.example.demo.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Map;

@Service
public class FreeMarkerService {
    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;

    public String renderTemplate(String templateName, Map<String, Object> model) throws IOException, TemplateException {
        Template template = configurer.getConfiguration().getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}

```

templates/freemarker-mail.html
```html
<div>
    Hi, My name is ${name}, I'm ${age} years old.
</div>
```


>参考
>[SpringBoot项目中集成FreeMarker实现模板发送邮件](https://riemann.blog.csdn.net/article/details/93380224)


## 发送邮件
```java
package com.example.demo.service;

import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MailServiceTest {

    @Autowired
    MailService mailService;

    @Autowired
    FreeMarkerService freeMarkerService;

    @Autowired
    ThymeleafService thymeleafService;

    @Test
    void sendMailByFreeMarkerRender() throws MessagingException, IOException, TemplateException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 23);

        String content = freeMarkerService.renderTemplate("freemarker-mail.html", map);

        String toUser = "1940xxxxxx@qq.com";
        String subject = "中秋快乐";

        mailService.sendHtmlMail(toUser, subject, content);
    }

    @Test
    void sendMailByThymeleafRender() throws MessagingException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 23);

        String content = thymeleafService.renderTemplate("thymeleaf-mail.html", map);

        String toUser = "1940xxxxxx@qq.com";
        String subject = "中秋快乐";

        mailService.sendHtmlMail(toUser, subject, content);
    }
}
```

相比较而言freemarker较thymeleaf更简单一些