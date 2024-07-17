package com.example.demo.config;

import com.example.demo.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Canal消息提供者，canal-server生产的消息通过RabbitMQ消息队列发送
 */
@Configuration
public class CanalProvider {
    /**
     * 队列
     */
    @Bean
    public Queue canalQueue() {
        /**
         * durable:是否持久化，默认false，持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在；暂存队列：当前连接有效
         * exclusive:默认为false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
         * autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除
         */
        return new Queue(RabbitConstant.CanalQueue, true);
    }

    /**
     * 交换机，这里使用直连交换机
     */
    @Bean
    DirectExchange canalExchange() {
        return new DirectExchange(RabbitConstant.CanalExchange, true, false);
    }

    /**
     * 绑定交换机和队列，并设置匹配键
     */
    @Bean
    Binding bindingCanal() {
        return BindingBuilder.bind(canalQueue()).to(canalExchange()).with(RabbitConstant.CanalRouting);
    }
}