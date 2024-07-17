package com.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.domain.Book;
import com.demo.service.BookService;
import com.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResultVO<List<Book>> getAll() {
        if (true) {
            throw new RuntimeException("服务器异常");
        }

        return ResultVO.success(bookService.list());
    }

    @PostMapping
    public ResultVO save(@RequestBody Book book) {
        return ResultVO.of(bookService.save(book));
    }

    @PutMapping
    public ResultVO update(@RequestBody Book book) {
        return ResultVO.of(bookService.update(book));
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return ResultVO.of(bookService.delete(id));
    }

    @GetMapping("/{id}")
    public ResultVO<Book> getById(@PathVariable Integer id) {
        return ResultVO.success(bookService.getById(id));
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public ResultVO<IPage<Book>> getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return ResultVO.success(bookService.getByPage(currentPage, pageSize));
    }
}
