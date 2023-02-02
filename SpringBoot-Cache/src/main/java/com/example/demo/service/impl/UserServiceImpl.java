package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    /**
     * 数据列表
     */
    private static List<User> list = new ArrayList<>();

    private int getUserIndex(Integer userId) {
        int index = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(userId)) {
                index = i;
                break;
            }
        }

        return index;
    }

    @Override
    public User addUser(User user) {
        list.add(user);
        return user;
    }


    @Override
    public void deleteUserById(Integer userId) {
        int index = this.getUserIndex(userId);

        if (index > -1) {
            list.remove(index);
        }
    }

    @Override
    public User getUserById(Integer userId) {

        int index = this.getUserIndex(userId);

        if (index > -1) {
            return list.get(index);
        }

        return null;
    }

    @Override
    public void updateUserById(User user) {
        int index = this.getUserIndex(user.getId());

        if (index > -1) {
            list.set(index, user);
        }
    }
}
