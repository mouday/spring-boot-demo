package com.example.demo.service;

import com.example.demo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ArticleServiceTests {

    @Autowired
    private ArticleService articleService;

    @Test
    void getArticleById() {
        Article article = articleService.getArticleById(1);
        System.out.println(article);
    }

    @Test
    void updateArticle() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("新的标题");

        Integer integer = articleService.updateArticle(article);
        System.out.println(integer);
    }

    @Test
    void getArticleList() {
        Article article = new Article();
        article.setUserId(1);

        List<Article> articleList = articleService.getArticleList(article);
        System.out.println(articleList);
    }

    @Test
    void deleteArticleById() {
        articleService.deleteArticleById(1);
    }

    @Test
    void insertArticle() {
        Article article = new Article();
        article.setUserId(1);
        article.setTitle("新的文章-1");
        article.setContent("新的文章内容-1");

        int id = articleService.insertArticle(article);
        System.out.println(article);
    }

}
