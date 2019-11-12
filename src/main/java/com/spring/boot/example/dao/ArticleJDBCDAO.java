package com.spring.boot.example.dao;

import com.spring.boot.example.model.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ArticleJDBCDAO {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void save(Article article) {
        //jdbcTemplate.update适合于insert 、update和delete操作；
        jdbcTemplate.update(" insert into article (author, title, content, create_time) values (?, ?, ?, ?)",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("delete from article where id = ?", id);
    }

    public void updateById(Article article) {
        jdbcTemplate.update("UPDATE article SET author = ?, title = ? ,content = ?,create_time = ? WHERE id = ?",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime(),
                article.getId());
    }

    public Article findById(Long id) {
        return (Article) jdbcTemplate.queryForObject("select * from article where id = ?", new Object[]{id},
                new BeanPropertyRowMapper(Article.class));
    }

    public List<Article> findAll() {
        return (List<Article>) jdbcTemplate.query("SELECT * FROM article ", new BeanPropertyRowMapper(Article.class));
    }
}
