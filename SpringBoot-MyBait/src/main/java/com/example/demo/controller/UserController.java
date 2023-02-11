package com.example.demo.controller;

import com.example.demo.dto.RequestPage;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 返回数据列表和分页信息
     * @param requestPage
     * @return
     */
    @GetMapping("/api/selectAllUserPage")
    public PageInfo<User> selectAllUserPage(RequestPage requestPage){
        PageHelper.startPage(requestPage.getPage(), requestPage.getSize());
        Page<User> page = userService.selectAllUserPage();
        PageInfo<User> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }

    /**
     * 返回数据列表
     * @param requestPage
     * @return
     */
    @GetMapping("/api/selectAllUserList")
    public List<User> selectAllUserList(RequestPage requestPage){
        PageHelper.startPage(requestPage.getPage(), requestPage.getSize());
        List<User> list = userService.selectAllUserList();
        return list;
    }
}