package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;
    private Integer user_id;
    private User user;

    private Integer article_id;
    private Article article;
    private String content;

    private Date create_time;
    private Date update_time;
}
