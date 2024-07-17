package com.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootTest
class BookMapperTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void getById(){
        Book book = bookMapper.selectById(1);
        System.out.println(book);
    }

    @Test
    public void save(){
        Book book = new Book();
        book.setTitle("明朝那些事");
        book.setAuthor("当年明月");

        int ret = bookMapper.insert(book);
        System.out.println(ret);
    }

    @Test
    public void delete(){
        int ret = bookMapper.deleteById(1);
        System.out.println(ret);
    }

    @Test
    public void update(){
        Book book = new Book();
        book.setId(2L);
        book.setTitle("水浒传2");

        int ret = bookMapper.updateById(book);
        System.out.println(ret);
    }

    @Test
    public void getAll(){
        List<Book> books = bookMapper.selectList(null);
        System.out.println(books);
    }

    @Test
    public void getPage(){
        IPage<Book> page = Page.of(2, 3);

        bookMapper.selectPage(page, null);
        // SELECT id,title,author FROM tb_book LIMIT ?,?

        System.out.println(page);
    }

    @Test
    public void getBy(){
        String name = "三国演义";

        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.hasText(name), Book::getTitle, name);

        List<Book> books = bookMapper.selectList(queryWrapper);
        System.out.println(books);
    }
}