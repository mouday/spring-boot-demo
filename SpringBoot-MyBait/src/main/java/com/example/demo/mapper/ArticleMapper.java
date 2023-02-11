package com.example.demo.mapper;

import com.example.demo.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * XML配置文件方式
 */
@Mapper
public interface ArticleMapper {
    Article getArticleById(Integer id);

    Integer updateArticle(Article article);

    List<Article> getArticleList(Article article);

    void deleteArticleById(Integer id);

    int insertArticle(Article article);
}
