package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    void save() {
        Book book = new Book();
        book.setTitle("明朝那些事");
        book.setAuthor("当年明月");

        boolean ret = bookService.save(book);
        System.out.println(ret);
    }

    @Test
    void update() {
        Book book = new Book();
        book.setId(2L);
        book.setTitle("水浒传2");

        boolean ret = bookService.update(book);
        System.out.println(ret);
    }

    @Test
    void delete() {
        boolean ret = bookService.delete(1);
        System.out.println(ret);
    }

    @Test
    void getById() {
        Book book = bookService.getById(1);
        System.out.println(book);
    }

    @Test
    void getAll() {
        List<Book> list = bookService.getAll();
        System.out.println(list);
    }

    @Test
    void getByPage() {
        IPage<Book> page = bookService.getByPage(1, 2);

        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }
}