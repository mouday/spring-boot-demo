package com.example.demo.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.JsonResult;
import com.example.demo.dto.UserDTO;

@RestController
public class UserController {
    @PostMapping("/save")
    public JsonResult saveUser(@RequestBody @Validated UserDTO userDTO) {
        // 校验通过，才会执行业务逻辑处理
        System.out.println(userDTO);

        return JsonResult.success(userDTO);
    }
}
