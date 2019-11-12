package com.spring.boot.example.service;

import com.spring.boot.example.model.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleJDBCServiceTest {

    @Autowired
    private ArticleJDBCService articleJDBCService;

    @Test
    public void testSave() {
        Article article = new Article();
        article.setAuthor("第一人");
        article.setContent("ffffff");
        article.setTitle("fff");
        article.setCreateTime(new Date());
        Article result = articleJDBCService.saveArticle(article);
    }

    @Test
    public void testGetAll() {
        List<Article> all = articleJDBCService.getAll();
        System.out.println(all);
    }
}
