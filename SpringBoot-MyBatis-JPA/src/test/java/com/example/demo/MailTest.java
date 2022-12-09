package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
public class MailTest {

    @Autowired
    private JavaMailSenderImpl mailSender;

    // 发送简单邮件
    @Test
    public void testSimpleMail(){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("邮件主题");
        message.setText("邮件内容");

        message.setFrom("moudayemail@163.com");
        message.setTo("mouday@qq.com");

        mailSender.send(message);
    }

    // 发送复杂邮件
    @Test
    public void testMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);


        helper.setSubject("邮件主题");
        helper.setText("<span style='color:red;'>邮件内容</span>", true);

        helper.setFrom("moudayemail@163.com");
        helper.setTo("mouday@qq.com");

        // 上传文件
        helper.addAttachment("file1", new File("/Users/hina/Desktop/timg.jpeg"));

        mailSender.send(mimeMessage);
    }
}
