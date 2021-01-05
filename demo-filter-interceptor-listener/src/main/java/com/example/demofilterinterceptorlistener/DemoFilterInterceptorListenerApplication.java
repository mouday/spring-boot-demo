package com.example.demofilterinterceptorlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DemoFilterInterceptorListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoFilterInterceptorListenerApplication.class, args);
    }
}
