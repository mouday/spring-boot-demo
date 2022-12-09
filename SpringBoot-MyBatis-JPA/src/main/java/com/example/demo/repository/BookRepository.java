package com.example.demo.repository;

import com.example.demo.pojo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
    List<Book> findByNameLike(String name);
}
