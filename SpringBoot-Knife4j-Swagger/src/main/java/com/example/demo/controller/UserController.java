package com.example.demo.controller;

import com.example.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户相关接口")
public class UserController {
    @GetMapping("/api/getUser")
    @ApiOperation("获取用户信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name="userId", value = "用户id", required = true, type = "String")
    )
    public User getUser(Integer userId) {
        User user = new User();
        user.setId(userId);
        user.setAge(20);
        user.setName("Tom");
        return user;
    }
}
