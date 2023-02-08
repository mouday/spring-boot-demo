package com.example.demo.controller;

import com.example.demo.entity.domain.User;
import com.example.demo.entity.dto.UserDto;
import com.example.demo.entity.vo.UserVo;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user/getUserById")
    public UserVo getUserById(@RequestBody UserDto userDto) {
        System.out.println(userDto);

        User user = userService.getById(userDto.getId());

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);

        return  userVo;
    }
}
