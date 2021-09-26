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