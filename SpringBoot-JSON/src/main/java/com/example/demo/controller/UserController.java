package com.example.demo.controller;


import com.example.demo.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@RestController
public class UserController {

    @GetMapping("/user")
    @ResponseBody
    public User getUser(){
        User user = new User();
        user.setAge(20);
        user.setName("tom");
        user.setPassword("123456");

        user.setBirthday(new Date());
        user.setLocalDateTime(LocalDateTime.now());
        user.setLocalDate(LocalDate.now());
        user.setLocalTime(LocalTime.now());
        user.setLocalDateTimeFormat(LocalDateTime.now());

        return user;
    }
}
