package com.example.demo.service;

import com.example.demo.entiry.User;
import com.example.demo.enums.SexEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void addUser(){
        User user = new User();
        user.setName("Tom");
        user.setSex(SexEnum.MAN);

        userService.save(user);
    }
}
