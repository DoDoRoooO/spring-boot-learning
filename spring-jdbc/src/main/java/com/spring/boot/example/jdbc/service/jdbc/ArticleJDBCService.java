package com.spring.boot.example.jdbc.service.jdbc;


import com.spring.boot.example.common.model.Article;
import java.util.List;

public interface ArticleJDBCService {

    Article saveArticle(Article article);

    void deleteArticle(Long id);

    void updateArticle(Article article);

    Article getArticle(Long id);

    List<Article> getAll();
}
