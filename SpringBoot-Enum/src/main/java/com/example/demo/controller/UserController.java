package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entiry.User;
import com.example.demo.enums.SexEnum;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfo")
    public User getUserInfo() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(User::getId);
        queryWrapper.last("limit 1");

        return userService.getOne(queryWrapper);
    }

    @GetMapping("/getUserList")
    public IPage<User> getUserList(
            @RequestParam(name = "sex", required = false) SexEnum sexEnum,
            @RequestParam(required = false, defaultValue="1") Integer currentPage,
            @RequestParam(required = false, defaultValue="10") Integer pageSize
    ) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        log.info("sexEnum: {}", sexEnum);

        if (sexEnum != null) {
            queryWrapper.eq(User::getSex, sexEnum.getCode());
        }

        queryWrapper.orderByDesc(User::getId);

        IPage<User> page = new Page<>(currentPage, pageSize);
        userService.page(page, queryWrapper);

        return page;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        log.info("user: {}", user);
        userService.save(user);
        return user;
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        userService.updateById(user);
        return user;
    }

    @GetMapping("/getUser")
    public User getUser(Integer id) {
        return userService.getById(id);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(Integer id) {
        userService.removeById(id);
    }
}
