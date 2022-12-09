package com.example.demo.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageService {
    // 接收消息数据
    @RabbitListener(queues={"message"})
    public void listenMessage(Map<String, Object> map){
        System.out.println("收到消息: " + map);
    }

    // 接收消息头和消息体
    @RabbitListener(queues={"message"})
    public void receiveMessage(Message message){
        System.out.println("收到消息: " + message.getMessageProperties());
        System.out.println("收到消息: " + message.getBody());
    }
}
