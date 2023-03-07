package com.example.demo.controller;

import com.example.demo.entity.EmailDto;
import com.example.demo.event.SendEmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/sendEmail")
    public String sendEmail() {
        EmailDto emailDto = new EmailDto();
        emailDto.setEmail("tom@qq.com");
        emailDto.setSubject("邮件标题");
        emailDto.setContent("邮件内容");

        // 发布事件
        publisher.publishEvent(new SendEmailEvent(emailDto));
        return "success";
    }
}
