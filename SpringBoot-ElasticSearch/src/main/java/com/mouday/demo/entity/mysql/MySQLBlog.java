package com.mouday.demo.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * CREATE TABLE `blog` (
 * `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
 * `title` varchar(60) DEFAULT NULL COMMENT '标题',
 * `author` varchar(60) DEFAULT NULL COMMENT '作者',
 * `content` text COMMENT '内容',
 * `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 * `update_time` datetime DEFAULT NULL COMMENT '更新时间',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
 */

@Data
@Table(name = "blog")
@Entity
public class MySQLBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;

    @Column(columnDefinition = "text")
    private String content;

    private Date createTime;
    private Date updateTime;
}
