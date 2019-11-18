package com.spring.boot.example.dao.jdbc;

import com.spring.boot.example.model.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ArticleJDBCDAO {

    public void save(Article article, JdbcTemplate jdbcTemplate) {
        //jdbcTemplate.update适合于insert 、update和delete操作；
        jdbcTemplate.update(" insert into article (author, title, content, create_time) values (?, ?, ?, ?)",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime());
    }

    public void deleteById(Long id, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("delete from article where id = ?", id);
    }

    public void updateById(Article article, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("UPDATE article SET author = ?, title = ? ,content = ?,create_time = ? WHERE id = ?",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime(),
                article.getId());
    }

    public Article findById(Long id, JdbcTemplate jdbcTemplate) {
        return (Article) jdbcTemplate.queryForObject("select * from article where id = ?", new Object[]{id},
                new BeanPropertyRowMapper(Article.class));
    }

    public List<Article> findAll(JdbcTemplate jdbcTemplate) {
        return (List<Article>) jdbcTemplate.query("SELECT * FROM article ", new BeanPropertyRowMapper(Article.class));
    }
}
