package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.response.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
