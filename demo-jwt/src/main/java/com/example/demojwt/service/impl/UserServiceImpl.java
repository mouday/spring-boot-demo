package com.example.demojwt.service.impl;

import com.example.demojwt.entity.User;
import com.example.demojwt.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 用户服务
 */
@Service
public class UserServiceImpl implements UserService {

    // 模拟数据库
    private static Map<Integer, User> data = new HashMap<>();

    static {
        User user = new User();
        user.setId(1);
        user.setName("Tom");
        user.setPassword("123456");

        data.put(user.getId(), user);
    }

    /**
     * 通过id获取用户
     *
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        return data.get(id);
    }

    /**
     * 根据姓名查询用户
     *
     * @param name
     * @return
     */
    public User getUserByName(String name) {

        for (Map.Entry<Integer, User> entry : data.entrySet()) {
            User user = entry.getValue();

            if (user.getName().equals(name)) {
                return user;
            }
        }

        return null;
    }

}
