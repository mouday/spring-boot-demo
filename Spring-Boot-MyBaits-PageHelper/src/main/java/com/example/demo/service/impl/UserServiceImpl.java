package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import com.example.demo.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public Page<User> selectAllUserPage() {
        return userDao.selectAllUserPage();
    }

    @Override
    public List<User> selectAllUserList() {
        return userDao.selectAllUserList();
    }
}
