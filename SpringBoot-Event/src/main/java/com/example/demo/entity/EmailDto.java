package com.example.demo.entity;

import lombok.Data;

@Data
public class EmailDto {
    private String email;
    private String subject;
    private String content;
}
