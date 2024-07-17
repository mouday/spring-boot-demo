package com.example.demo.controller;

import com.example.demo.PersonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${server.port}")
    private Integer port;

    // http://localhost:8081
    @Value("${server-address}")
    private String serverAddress;

    @Autowired
    private Environment environment;

    @Autowired
    private PersonConfig personConfig;

    @GetMapping("/")
    public String index() {
        return "Hello 2:" + serverAddress;
    }

    @GetMapping("/env")
    public String env() {
        return environment.getProperty("server.port");
    }

    @GetMapping("/userConfig")
    public PersonConfig userConfig() {
        return personConfig;
    }

}
