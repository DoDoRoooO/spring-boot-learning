package com.spring.boot.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName SecurityConfig.java
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            // 表单登录 自定义登录页面
            .formLogin().loginPage("/login.html")
            // 自定义登录逻辑
            .loginProcessingUrl("/login")
            // 登录成功跳转页面 必须是post方式
            .successForwardUrl("/main")
            // 登录失败跳转页面 必须 post方式
            .failureForwardUrl("/loginFail")

            .and()
            .authorizeRequests()
                // 放行登录页面
                .antMatchers("/login.html").permitAll()
                // 放行登录失败页面
                .antMatchers("/loginFail.html").permitAll()
                // 所有请求都必须被认证
                .anyRequest().authenticated()

            .and()
            // 关闭csrf 防护
            .csrf().disable()
        ;

    }
}
