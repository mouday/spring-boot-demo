package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    public User addUser(User user);

    public void deleteUserById(Integer userId);

    public User getUserById(Integer userId);

    void updateUserById(User user);
}
