package com.example.demo.service;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    void getUserById() {
        Integer userId = 1;
        User user = userService.getUserById(userId);
        System.out.println(user);
        // User(id=1, name=曹操, age=40, create_time=null, update_time=null)
    }
}
