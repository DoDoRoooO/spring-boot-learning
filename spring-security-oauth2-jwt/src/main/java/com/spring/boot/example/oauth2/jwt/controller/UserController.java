package com.spring.boot.example.oauth2.jwt.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;
import java.nio.charset.StandardCharsets;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public Object getUser(@RequestHeader( value = "Authorization") String authorization,
                          Authentication authentication) {
        if (StringUtils.hasText(authorization)) {
            final String token = authorization.substring(authorization.indexOf("Bearer") + 7);
            return Jwts.parser()
                       .setSigningKey("demo".getBytes(StandardCharsets.UTF_8))
                       .parseClaimsJws(token).getBody();
        }
        return authentication.getPrincipal();
    }

}
