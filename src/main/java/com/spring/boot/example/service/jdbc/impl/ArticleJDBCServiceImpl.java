package com.spring.boot.example.service.jdbc.impl;

import com.spring.boot.example.dao.jdbc.ArticleJDBCDAO;
import com.spring.boot.example.model.Article;
import com.spring.boot.example.service.jdbc.ArticleJDBCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ArticleJDBCServiceImpl implements ArticleJDBCService {

    @Resource
    private ArticleJDBCDAO articleJDBCDAO;
    @Resource
    private JdbcTemplate primaryJdbcTemplate;
    @Resource
    private JdbcTemplate secondaryJdbcTemplate;

    @Transactional
    @Override
    public Article saveArticle(Article article) {
        log.info("ArticleJDBCServiceImpl#saveArticle(), param={}", article);
        articleJDBCDAO.save(article, primaryJdbcTemplate);
        articleJDBCDAO.save(article, secondaryJdbcTemplate);
        if (article.getId() == null) {
            throw new RuntimeException();
        }
        return article;
    }

    @Transactional
    @Override
    public void deleteArticle(Long id) {
        log.info("ArticleJDBCServiceImpl#deleteArticle(), param={}", id);
        articleJDBCDAO.deleteById(id, primaryJdbcTemplate);
        articleJDBCDAO.deleteById(id, secondaryJdbcTemplate);
    }

    @Transactional
    @Override
    public void updateArticle(Article article) {
        log.info("ArticleJDBCServiceImpl#updateArticle(), param={}", article);
        articleJDBCDAO.updateById(article, primaryJdbcTemplate);
        articleJDBCDAO.updateById(article, secondaryJdbcTemplate);
    }

    @Override
    public Article getArticle(Long id) {
        log.info("ArticleJDBCServiceImpl#getArticle(), param={}", id);
        return articleJDBCDAO.findById(id, secondaryJdbcTemplate);
    }

    @Override
    public List<Article> getAll() {
        log.info("ArticleJDBCServiceImpl#getAll()");
        return articleJDBCDAO.findAll(secondaryJdbcTemplate);
    }
}
