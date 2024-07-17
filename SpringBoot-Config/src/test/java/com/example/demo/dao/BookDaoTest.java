package com.example.demo.dao;

import com.example.demo.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Test
    public void save() {
        Book book = bookDao.getById(2L);
        System.out.println(book);
        // Book(id=2, title=水浒传, author=施耐庵)
    }
}
