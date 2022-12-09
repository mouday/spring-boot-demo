package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SecurityController {

    @GetMapping("/vip/text")
    public String vip() {
        return "vip";
    }

    @GetMapping("/vip1/text")
    public String vip1() {
        return "vip1";
    }

    @GetMapping("/vip2/text")
    public String vip2() {
        return "vip2";
    }

    @GetMapping("/vip3/text")
    public String vip3() {
        return "vip3";
    }
}
