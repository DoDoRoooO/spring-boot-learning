package com.spring.boot.example.controller;

import com.spring.boot.example.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
/*
This annotation tells Spring Boot to “guess” how you want to configure Spring, based on the jar dependencies that you have added.
Since spring-boot-starter-web added Tomcat and Spring MVC, the auto-configuration assumes that you are developing a web application
and sets up Spring accordingly.
*/
@EnableAutoConfiguration
public class ExampleController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/article")
    Article getArticle() {
        log.info("ExampleController#getAriticle() start");
        Article article = new Article().builder().id(1433L).author("harry port").build();
        log.info("ExampleController#getAriticle() end  result={}", article);
        return article;
    }
}
