package com.example.demo.entity.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 数据库实体映射
 */
@Data
@TableName("tb_user")
public class User {
    private Long id;

    private String name;

    private Integer age;
}
