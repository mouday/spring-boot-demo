package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") Integer id){
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            return user.get();
        } else{
            return null;
        }
    }

    @GetMapping("/user")
    public User insertUser(User user){
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
