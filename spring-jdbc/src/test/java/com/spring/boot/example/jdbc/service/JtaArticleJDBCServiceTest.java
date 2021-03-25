package com.spring.boot.example.jdbc.service;

import com.spring.boot.example.common.model.Article;
import com.spring.boot.example.jdbc.service.jdbc.ArticleJDBCService;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JtaArticleJDBCServiceTest {

    @Autowired
    private ArticleJDBCService articleJDBCService;

    @Test
    public void testSave() {
        Article article = new Article();
        article.setAuthor("第六人");
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
