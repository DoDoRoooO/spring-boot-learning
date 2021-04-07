package com.spring.boot.example.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController.java
 */

@Controller
public class LoginController {


    // 必须 `ROLE_`开头
    @Secured("ROLE_admin")
    // access() 表达式，不需要`ROLE_`开头
    @PreAuthorize("hasRole('root')")
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

    @RequestMapping(value = "/demo")
    public String demo() {
        return "demo";
    }

    @RequestMapping(value = "/showLogin")
    public String showLogin() {
        return "login";
    }
}
