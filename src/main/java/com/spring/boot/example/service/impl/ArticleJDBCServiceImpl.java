package com.spring.boot.example.service.impl;

import com.spring.boot.example.dao.ArticleJDBCDAO;
import com.spring.boot.example.model.Article;
import com.spring.boot.example.service.ArticleJDBCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ArticleJDBCServiceImpl implements ArticleJDBCService {

    @Autowired
    private ArticleJDBCDAO articleJDBCDAO;

    @Transactional
    @Override
    public Article saveArticle(Article article) {
        log.info("ArticleJDBCServiceImpl#saveArticle(), param={}", article);
        articleJDBCDAO.save(article);
        if (article.getId() == null) {
            throw new RuntimeException();
        }
        return article;
    }

    @Transactional
    @Override
    public void deleteArticle(Long id) {
        log.info("ArticleJDBCServiceImpl#deleteArticle(), param={}", id);
        articleJDBCDAO.deleteById(id);
    }

    @Transactional
    @Override
    public void updateArticle(Article article) {
        log.info("ArticleJDBCServiceImpl#updateArticle(), param={}", article);
        articleJDBCDAO.updateById(article);
    }

    @Override
    public Article getArticle(Long id) {
        log.info("ArticleJDBCServiceImpl#getArticle(), param={}", id);
        return articleJDBCDAO.findById(id);
    }

    @Override
    public List<Article> getAll() {
        log.info("ArticleJDBCServiceImpl#getAll()");
        return articleJDBCDAO.findAll();
    }
}
