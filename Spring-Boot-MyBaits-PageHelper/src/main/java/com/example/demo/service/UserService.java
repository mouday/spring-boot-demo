package com.example.demo.service;

import com.example.demo.bean.User;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserService {
    Page<User> selectAllUserPage();

    List<User> selectAllUserList();
}
