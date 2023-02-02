package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private UserService userService;

    /**
     * CachePut 将返回值放入缓存
     * value 缓存名称,一个名称下可以有多个key
     * key 缓存key, 支持 Spring Expression Language (SpEL)
     *
     * @param user
     * @return
     */
    @CachePut(value = "user", key = "#user.id")
    // @CachePut(value = "user", key = "#p0.id")
    // @CachePut(value = "user", key = "#result.id")
    // @CachePut(value = "user", key = "#root.args[0].id")
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * CacheEvict 清理指定缓存
     *
     * @param user
     */
    @CacheEvict(value = "user", key = "#user.id")
    @PostMapping("/deleteUserById")
    public void deleteUserById(@RequestBody User user) {
        userService.deleteUserById(user.getId());
    }

    /**
     * CacheEvict 清理指定缓存
     *
     * @param user
     */
    @CacheEvict(value = "user", key = "#user.id")
    @PostMapping("/updateUserById")
    public void updateUserById(@RequestBody User user) {
        userService.updateUserById(user);
    }

    /**
     * 方法执行前，先查询缓存，有数据直接返回，没有数据继续执行方法
     * condition 满足指定条件时才缓存数据
     *
     * @param user
     * @return
     */
    @Cacheable(value = "user", key = "#user.id", unless = "#result == null")
    @PostMapping("/getUserById")
    public User getUserById(@RequestBody User user) {
        System.out.println("getUserById");
        return userService.getUserById(user.getId());
    }

    @Cacheable(value = "user", key = "#user.id + '_' + #user.name", unless = "#result == null")
    @PostMapping("/getUserList")
    public List<User> getUserList(@RequestBody User user) {
        System.out.println("getUserById");
        return null;
    }
}
