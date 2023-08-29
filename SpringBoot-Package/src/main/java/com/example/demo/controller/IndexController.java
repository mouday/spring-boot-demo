package com.example.demo.controller;

import com.example.demo.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private AppConfig appConfig;

    @GetMapping("/")
    public String index() {
        return "Hello: " + appConfig.getName();
    }
}
