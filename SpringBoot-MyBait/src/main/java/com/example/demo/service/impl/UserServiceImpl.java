package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> selectAllUserPage() {
        return userMapper.selectAllUserPage();
    }

    @Override
    public List<User> selectAllUserList() {
        return userMapper.selectAllUserList();
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }
}
