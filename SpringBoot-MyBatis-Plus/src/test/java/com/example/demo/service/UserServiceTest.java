package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    /**
     * 插入数据
     */
    @Test
    void testSave() {
        User user = new User();
        user.setName("Tom");
        user.setAge(20);

        userService.save(user);
        // INSERT INTO tb_user ( name, age ) VALUES ( ?, ? )
    }

    /**
     * 字段更新
     */
    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(1L);
        user.setAge(22);
        // 仅更新非空的属性
        userService.updateById(user);
        // UPDATE tb_user SET age=? WHERE id=?
    }

    /**
     * 部分字段更新
     */
    @Test
    void testUpdateFieldById() {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, 1);
        updateWrapper.set(User::getAge, 30);

        userService.update(updateWrapper);
        // UPDATE tb_user SET age=? WHERE (id = ?)
    }

    /**
     * 查询单条数据
     */
    @Test
    void testGetById() {
        User user = userService.getById(1L);
        // SELECT id,name,age FROM tb_user WHERE id=?

        System.out.println(user);
        // User(id=1, name=Tom, age=22)
    }

    /**
     * 查询多条数据
     */
    @Test
    void testSelect() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(User::getAge, 20);

        // 2次SQL，一次查总记录数，一次查具体数据
        // public Page(long current, long size, boolean searchCount)
        Page<User> page = new Page<>(1, 10);

        userService.page(page, queryWrapper);
        // SELECT COUNT(*) AS total FROM tb_user WHERE (age > ?)
        // SELECT id,name,age FROM tb_user WHERE (age > ?) LIMIT ?

        long total = page.getTotal();
        List<User> records = page.getRecords();

        System.out.println(total);
        // 1

        System.out.println(records);
        // [User(id=1, name=Tom, age=22)]
    }
}
