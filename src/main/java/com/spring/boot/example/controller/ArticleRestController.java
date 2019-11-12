package com.spring.boot.example.controller;

import com.spring.boot.example.config.base.AjaxResponse;
import com.spring.boot.example.model.Article;
import com.spring.boot.example.service.ArticleJDBCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/rest")
public class ArticleRestController {

    @Resource
    private ArticleJDBCService articleJDBCService;

    @PostMapping("/article")
    public AjaxResponse saveArticle(@RequestBody Article article) {
        log.info("ArticleRestController#saveArticle(): {}", article);
        Article result = articleJDBCService.saveArticle(article);
        log.info("ArticleRestController#saveArticle() return :{}", result);
        return AjaxResponse.success(result);
    }

    @DeleteMapping("/article/{id}")
    public AjaxResponse deleteArticle(@PathVariable Long id) {
        log.info("ArticleRestController#deleteArticle() : {}", id);
        log.info("ArticleRestController#deleteArticle() return :{}", id);
        return AjaxResponse.success(id);
    }

    @PutMapping("/article/{id}")
    public AjaxResponse updateArticle(@PathVariable Long id, @RequestBody Article article) {
        log.info("ArticleRestController#updateArticle() : {}, {}", id, article);
        log.info("ArticleRestController#updateArticle() return :{}", article);
        return AjaxResponse.success(id);
    }


    @GetMapping("/article/{id}")
    public AjaxResponse getArticle(@PathVariable Long id) {
        log.info("ArticleRestController#getArticle() : {}", id);
        Article article = Article.builder().id(1L).author("zimug").content("spring boot 2.深入浅出").title("t1").build();
        log.info("ArticleRestController#getArticle() return :{}", article);
        return AjaxResponse.success(article);
    }
}
