package com.example.demo.listener;

import com.example.demo.entity.EmailDto;
import com.example.demo.event.SendEmailEvent;
import com.example.demo.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 */
@Component
public class SendEmailListener implements ApplicationListener<SendEmailEvent> {
    @Autowired
    private SendEmailService sendEmailService;

    @Async
    @Override
    public void onApplicationEvent(SendEmailEvent event) {
        EmailDto emailDto = event.getEmailDto();
        this.sendEmailService.sendEmail(emailDto);
    }
}
