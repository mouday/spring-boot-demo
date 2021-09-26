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
