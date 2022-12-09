package com.example.demojwt.controller;

import com.example.demojwt.annotation.PassToken;
import com.example.demojwt.entity.User;
import com.example.demojwt.service.UserService;
import com.example.demojwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关控制器
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 登录 不需要验证
     * @param user
     * @return
     */
    @PassToken
    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        System.out.println(user);

        Map<String, Object> map = new HashMap<>();

        User loginUser = userService.getUserByName(user.getName());
        if(loginUser == null){
            map.put("msg", "用户不存在");
            return map;
        }

        if(!loginUser.getPassword().equals(user.getPassword())){
            map.put("msg", "登录失败，密码错误");
        } else{
            map.put("token", JwtUtil.sign(user.getId(), user.getName()));
        }

        return map;
    }

    /**
     * 登陆后的操作，默认需要验证
     * @return
     */
    @GetMapping("/message")
    public String getMessage() {
        return "验证通过";
    }
}
