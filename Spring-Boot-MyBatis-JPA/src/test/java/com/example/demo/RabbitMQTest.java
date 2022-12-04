package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RabbitMQTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
        // 创建交换器
        amqpAdmin.declareExchange(new DirectExchange("exchange.admin"));

        // 创建queque
        amqpAdmin.declareQueue(new Queue("queue.admin"));

        // 创建绑定规则
        amqpAdmin.declareBinding(new Binding("queue.admin", Binding.DestinationType.QUEUE, "exchange.admin", "admin", null));

        System.out.println("创建成功");
    }

    // 单播：发送数据
    @Test
    public void testSendRabbitMQ() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 23);

        rabbitTemplate.convertAndSend("exchange.message", "message", map);
    }

    // 单播：接收数据
    @Test
    public void testReceiveRabbitMQ() {
        Object obj = rabbitTemplate.receiveAndConvert("message");
        System.out.println(obj);
        // {name=Tom, age=23}
    }


}
