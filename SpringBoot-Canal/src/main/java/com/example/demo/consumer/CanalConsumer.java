package com.example.demo.consumer;

import com.example.demo.constant.EventTypeConstant;
import com.example.demo.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Canal消息消费者
 */
@Component
@RabbitListener(queues = RabbitConstant.CanalQueue)
public class CanalConsumer {
    @RabbitHandler
    public void process(Map<String, Object> msg) {
        System.out.println("收到canal消息：" + msg);
        // 收到canal消息：{data=[{id=21, name=Mini+1, age=30}], database=data, es=1684302821000, id=2, isDdl=false, mysqlType={id=int unsigned, name=varchar(20), age=tinyint unsigned}, old=[{name=Mini}], pkNames=[id], sql=, sqlType={id=4, name=12, age=-6}, table=tb_user, ts=1684302821452, type=UPDATE}

        boolean isDdl = (boolean) msg.get("isDdl");

        // 不处理DDL事件
        if (isDdl) {
            return;
        }

        // 数据库
        String database = (String) msg.get("database");
        // 表
        String table = (String) msg.get("table");
        // 类型：INSERT/UPDATE/DELETE
        String type = (String) msg.get("type");
        // 每一列的数据值
        List<?> data = (List<?>) msg.get("data");

        System.out.printf("%s.%s", database, table);

        // 只处理指定类型
        if (EventTypeConstant.INSERT.equalsIgnoreCase(type)) {
            System.out.println("INSERT");
        } else if (EventTypeConstant.UPDATE.equalsIgnoreCase(type)) {
            System.out.println("UPDATE");
        } else if (EventTypeConstant.DELETE.equalsIgnoreCase(type)) {
            System.out.println("DELETE");
        } else {
            // 其他事件
        }
    }
}