package com.example.demo.service.impl;

import com.example.demo.entity.Article;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public Integer updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    public List<Article> getArticleList(Article article) {
        return articleMapper.getArticleList(article);
    }

    @Override
    public void deleteArticleById(Integer id) {
        articleMapper.deleteArticleById(id);
    }

    @Override
    public int insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }
}
