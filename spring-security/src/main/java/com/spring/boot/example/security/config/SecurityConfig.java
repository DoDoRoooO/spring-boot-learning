package com.spring.boot.example.security.config;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @ClassName SecurityConfig.java
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAccessDeniedHandler accessDeniedHandler;

    @Resource
    private MyAuthenticationFailureHandler authenticationFailureHandler;

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
            // 自定义登录成功跳转逻辑
//            .successHandler(new MyAuthenticationSuccessHandler("http://www.baidu.com"))
            // 登录失败跳转页面 必须 post方式
//            .failureForwardUrl("/loginFail")
            // 自定义登录失败逻辑
            .failureHandler(authenticationFailureHandler)
            // 自定义登录用户名参数
//            .usernameParameter("")
            // 自定义登录密码参数名
//            .passwordParameter("")

            .and()
            .authorizeRequests()
                // 放行登录页面
                .antMatchers("/login.html").permitAll()
                // 放行登录失败页面
                .antMatchers("/loginFail.html").permitAll()
//                .antMatchers(HttpMethod.POST, "/login.html").permitAll()
//                .regexMatchers(HttpMethod.POST, ".+[.]png")
                // spring.mvv.servlet.path
//                .mvcMatchers("/images/**").servletPath("/xxx").permitAll()
                // 角色必须`ROLE_`开头
//                .antMatchers("/main.html").hasRole("root")
//                .antMatchers("/main.html").access("hasRole('root')")
                .antMatchers("/images/**").hasRole("root")
                .antMatchers("/images/**").hasAnyRole("root", "admin")
                .antMatchers("/images/**").hasAuthority("admin")
                .antMatchers("/images/**").hasAnyAuthority("root", "admin")

                // access 使用自定义权限认证
                .anyRequest().access("@myAccessPermissionServiceImpl.hasPermission(request, authentication)")

                // 所有请求都必须被认证
//                .anyRequest().authenticated()

            .and()

            // 自定义异常处理
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)

            .and()
            // 关闭csrf 防护
            .csrf().disable()
        ;

    }
}
