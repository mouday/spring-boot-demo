package com.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_book")
public class Book {
    private Long id;

    private String title;

    private String author;
}
