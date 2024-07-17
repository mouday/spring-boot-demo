package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 注解方式
 */
@Mapper
public interface UserMapper {

    @Select("select * from tb_user where id = #{id}")
    User selectUserById(Integer id);

    @Select("select * from tb_user")
    Page<User> selectAllUserPage();

    @Select("select * from tb_user")
    List<User> selectAllUserList();
}
