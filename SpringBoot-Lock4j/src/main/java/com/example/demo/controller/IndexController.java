package com.example.demo.controller;

import com.baomidou.lock.annotation.Lock4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    // 用户在5秒内只能访问1次
    @GetMapping("/lockMethod")
    @Lock4j(keys = {"#userId"}, acquireTimeout = 0, expire = 5000, autoRelease = false)
    public String lockMethod(@RequestParam String userId) {
        return userId;
    }
}
