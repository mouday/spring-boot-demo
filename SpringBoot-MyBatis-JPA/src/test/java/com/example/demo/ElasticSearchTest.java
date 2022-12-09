package com.example.demo;


import com.example.demo.pojo.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ElasticSearchTest {
    @Autowired
    public BookRepository bookRepository;


    @Test
    public void testSave(){
        Book book = new Book();
        book.setId(1);
        book.setName("<tom>");
        book.setAuthor("Jack");

        bookRepository.save(book);
    }

    @Test
    public void testQuery01(){
        Optional result= bookRepository.findById(1);
        if(result.isPresent()){
            System.out.println(result.get());
        }
    }

    @Test
    public void testQuery02(){
        List<Book> list =  bookRepository.findByNameLike("To");
        System.out.println(list);
    }

}
