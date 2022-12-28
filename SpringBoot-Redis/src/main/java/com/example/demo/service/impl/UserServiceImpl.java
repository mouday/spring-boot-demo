package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        log.info("DataServiceImpl getUser");

        // 模拟耗时操作
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName("Tom");
        user.setAge(20);
        user.setBirthday(new Date());

        return user;
    }
}
