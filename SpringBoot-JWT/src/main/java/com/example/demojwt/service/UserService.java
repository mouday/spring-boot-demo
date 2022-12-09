package com.example.demojwt.service;

import com.example.demojwt.entity.User;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 通过id获取用户
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 根据姓名查询用户
     *
     * @param name
     * @return
     */
    User getUserByName(String name);

}
