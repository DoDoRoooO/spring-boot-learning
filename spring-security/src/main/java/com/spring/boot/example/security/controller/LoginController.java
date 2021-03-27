package com.spring.boot.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController.java
 */

@Controller
public class LoginController {


    @RequestMapping(value = "/main")
    public String login() {
        // 必须是重定向
        return "redirect:main.html";
    }

    @RequestMapping(value = "/loginFail")
    public String fail() {
        // 必须是重定向
        return "redirect:loginFail.html";
    }
}
