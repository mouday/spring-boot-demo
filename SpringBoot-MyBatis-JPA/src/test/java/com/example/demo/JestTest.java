package com.example.demo;


import com.example.demo.pojo.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;


@SpringBootTest
public class JestTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void createTest(){
        Book book = new Book();

        bookRepository.index(book);
    }
}
