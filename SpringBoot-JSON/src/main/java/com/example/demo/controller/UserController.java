package com.example.demo.controller;


import com.example.demo.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    @ResponseBody
    public User getUser(){
        User user = new User();
        user.setAge(20);
        user.setName("tom");

        return user;
    }
}
