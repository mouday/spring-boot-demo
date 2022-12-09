package com.example.demo;

import com.example.demo.pojo.Employee;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTest {

    // k-v字符串形式
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // k-v对象形式
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<Object, Employee> employeeRedisTemplate;

    @Test
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("name", "Tom");
        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    public void testRedisSerializer(){
        Employee employee = new Employee();
        employee.setName("Tom");
        employee.setAge(23);
        employeeRedisTemplate.opsForValue().set("emp", employee);

    }

}
