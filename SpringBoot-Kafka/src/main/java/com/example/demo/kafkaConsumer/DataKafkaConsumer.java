package com.example.demo.kafkaConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class DataKafkaConsumer {
    @KafkaListener(topics = {"data_topic"})
    public void consumer(ConsumerRecord<String, String> consumerRecord) {
        System.out.println(consumerRecord.toString());

    }
}
