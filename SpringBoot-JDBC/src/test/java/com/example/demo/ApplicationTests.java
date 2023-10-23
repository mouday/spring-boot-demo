package com.example.demo;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加数据
     */
    @Test
    void testAdd() {
        String sql = "insert into t_user (name, age, sex) values (?, ?, ?)";
        int update = jdbcTemplate.update(sql, "张三", 20, "男");
        System.out.println(update); // 1
    }

    /**
     * 更新数据
     */
    @Test
    void testUpdate() {
        String sql = "update t_user set name = ? where id = ?";
        int update = jdbcTemplate.update(sql, "李四", 1);
        System.out.println(update); // 1
    }

    /**
     * 删除数据
     */
    @Test
    void testDelete() {
        String sql = "delete from t_user where id = ?";
        int update = jdbcTemplate.update(sql, 1);
        System.out.println(update); // 1
    }

    /**
     * 查询单条数据
     */
    @Test
    void testSelectOne() {
        String sql = "select * from t_user where id = ?";
        User user = jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                1);

        System.out.println(user);
        // User(id=1, name=张三, age=20, sex=男)
    }

    /**
     * 查询多条数据
     */
    @Test
    void testSelectList() {
        String sql = "select * from t_user where id = ?";
        List<User> user = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                1);

        System.out.println(user);
        // [User(id=1, name=张三, age=20, sex=男)]
    }

    /**
     * 查询单个数据
     */
    @Test
    void testSelectCount() {
        String sql = "select count(*) from t_user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
        // 1
    }
}
