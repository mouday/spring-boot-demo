package com.example.demo.entity;

import javax.persistence.*;

// 使用JPA注解配置映射关系
@Entity // 实体类
@Table(name = "tbl_user") // 指定表名
public class User {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
    private Integer id;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column // 默认类名=属性名
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
