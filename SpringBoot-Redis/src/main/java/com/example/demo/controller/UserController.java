package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser() {
        String key = "data";

        User user = redisUtils.get(key, User.class);

        log.info("RedisController getUser: {}", user);

        if (user == null) {
            // 数据不存在，获取数据
            user = userService.getUser();
            redisUtils.set(key, user);
        }

        return user;
    }

}
