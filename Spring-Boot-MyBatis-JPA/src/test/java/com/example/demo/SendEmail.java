package com.example.demo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static Properties getProperties(){

        Properties properties = new Properties();

        // 开启debug调试
        properties.setProperty("mail.debug", "true");
        // 邮件服务器
        properties.setProperty("mail.smtp.host", "smtp.163.com");
        // 端口号
        properties.setProperty("mail.smtp.port", "25");
        // 需要身份验证
        properties.setProperty("mail.smtp.auth", "true");

        // 发送邮件协议
        properties.setProperty("mail.transport.protocol", "smtp");

        return properties;

    }

    public static void main(String[] args) {

        // 发件人
        String fromUser = "moudayemail@163.com";
        // 客户端授权码
        String password = "mouday123";
        // 收件人
        String toUser = "mouday@qq.com";

        // 获取默认session对象
        Session session = Session.getInstance(getProperties());

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // 发送人
            message.setFrom(new InternetAddress(fromUser));

            // 接收人
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toUser));

            // 标题
            message.setSubject("This is the Subject Line!");

            // 消息体
            message.setText("This is actual message");

            // 发送消息
            Transport transport = session.getTransport();
            transport.connect(fromUser, password);
            transport.sendMessage(message, new Address[]{new InternetAddress(toUser)});
            transport.close();

            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}