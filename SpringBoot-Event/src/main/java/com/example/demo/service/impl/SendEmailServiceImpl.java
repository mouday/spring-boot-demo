package com.example.demo.service.impl;

import com.example.demo.entity.EmailDto;
import com.example.demo.service.SendEmailService;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {
    @Override
    public void sendEmail(EmailDto emailDto) {
        try {
            // 模拟耗时3秒
            Thread.sleep(3 * 1000);
        } catch (Exception e) {
            System.out.println("Email发送异常");
        }

        System.out.println("Email发送成功 " + emailDto);
    }
}
