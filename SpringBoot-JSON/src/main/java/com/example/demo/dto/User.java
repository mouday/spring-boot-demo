package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class User {
    private String name;

    private Integer age;

    // 忽略属性
    @JsonIgnore
    private String password;

    // 修改属性名
    @JsonProperty("birthDay")
    private Date birthday;

    private LocalDateTime localDateTime;

    private LocalDate localDate;

    private LocalTime localTime;

    // 格式化
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private LocalDateTime localDateTimeFormat;

}
