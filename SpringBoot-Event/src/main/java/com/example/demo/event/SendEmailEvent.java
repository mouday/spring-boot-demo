package com.example.demo.event;

import com.example.demo.entity.EmailDto;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 */
public class SendEmailEvent extends ApplicationEvent {
    private EmailDto emailDto;

    public SendEmailEvent(EmailDto emailDto) {
        super(emailDto);
        this.emailDto = emailDto;
    }

    public EmailDto getEmailDto() {
        return this.emailDto;
    }
}
