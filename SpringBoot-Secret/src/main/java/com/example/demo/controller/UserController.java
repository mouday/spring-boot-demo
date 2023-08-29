package com.example.demo.controller;

import com.example.demo.annotation.SecretData;
import com.example.demo.response.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 对该Controller中的所有方法进行加解密处理
 */
@RestController
@SecretData
public class UserController {

    @GetMapping("/user/getUser")
    public JsonResult getUser() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Tom");
        user.put("age", "18");

        return JsonResult.success(user);
    }

    @PostMapping("/user/addUser")
    public Object addUser(@RequestBody Map<String, String> data) {
        System.out.println(data);
        return data;
    }
}
