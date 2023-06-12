package com.example.demofilterinterceptorlistener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        System.out.println("IndexController");
        return "index";
    }
}
