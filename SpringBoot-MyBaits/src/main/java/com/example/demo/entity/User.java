package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;

    private Date create_time;
    private Date update_time;
}
