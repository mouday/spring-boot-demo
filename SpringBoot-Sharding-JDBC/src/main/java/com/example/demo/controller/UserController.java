package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        System.out.println(user);
        userService.save(user);

        return user;
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id){
        User user = userService.getById(id);
        user.setId(14L);
        return user;
    }
}
