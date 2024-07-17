package com.example.demo.service;

import com.example.demo.entity.Article;

import java.util.List;

public interface ArticleService {
    Article getArticleById(Integer id);

    List<Article> getArticleList(Article article);

    Integer updateArticle(Article article);

    void deleteArticleById(Integer id);

    int insertArticle(Article article);
}
