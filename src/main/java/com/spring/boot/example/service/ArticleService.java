package com.spring.boot.example.service;

import com.spring.boot.example.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class ArticleService {

    public Article saveArticle(@RequestBody Article article) {
        log.info("ArticleService#saveArticle(): {}", article);
        log.info("ArticleService#saveArticle() return :{}", article);
        return article;
    }
}
