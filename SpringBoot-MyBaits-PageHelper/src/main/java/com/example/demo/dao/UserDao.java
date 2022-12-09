package com.example.demo.dao;


import com.example.demo.bean.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from user")
    Page<User> selectAllUserPage();

    @Select("select * from user")
    List<User> selectAllUserList();
}
