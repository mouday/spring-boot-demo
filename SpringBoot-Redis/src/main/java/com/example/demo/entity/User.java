package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 生日
    private Date birthday;
}
