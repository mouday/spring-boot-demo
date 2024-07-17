package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer id;
    private Integer userId;
    private User user;
    private String title;
    private String content;
    // private List<Comment> commentList;

    private Date create_time;
    private Date update_time;
}
