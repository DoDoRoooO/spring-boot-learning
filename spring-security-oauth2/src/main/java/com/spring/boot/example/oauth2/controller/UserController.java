package com.spring.boot.example.oauth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController.java
 * @createTime 2021年04月14日 23:29:00
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getCurrentUser")
    public Object getUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

}
