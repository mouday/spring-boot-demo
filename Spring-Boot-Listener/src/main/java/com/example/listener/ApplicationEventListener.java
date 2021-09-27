package com.example.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * springboot 生命周期
 * 项目启动后，打印访问路径和端口
 */
@Component
public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 应用已启动完成
        if (event instanceof ApplicationReadyEvent) {
            Environment environment = applicationContext.getBean(Environment.class);

            String port = environment.getProperty("local.server.port");
            String address = environment.getProperty("local.server.address", "localhost");

            System.out.println(String.format("running at：http://%s:%s", address, port));
        }
    }

}