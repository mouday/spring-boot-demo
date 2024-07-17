package com.example.demo.dao;

import com.example.demo.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookDao {
    @Select("select * from tb_book where id = #{id}")
    public Book getById(Long id);
}
