package com.spring.boot.example.security.config;

import com.spring.boot.example.security.service.DefaultUserServiceImpl;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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

    @Resource
    private DefaultUserServiceImpl userService;

    @Resource
    private DataSource dataSource;

    @Resource
    private PersistentTokenRepository tokenRepository;


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
//                .anyRequest().access("@myAccessPermissionServiceImpl.hasPermission(request, authentication)")

                // 所有请求都必须被认证
                .anyRequest().authenticated()

            .and()
            // remember me
            .rememberMe()
                // 自定义参数名称
//                .rememberMeParameter()
                // 自定义失效时间 默认两周
//                .tokenValiditySeconds()
                // 自定义remember-me 功能实现
//                .rememberMeServices()
                // 自定义登录逻辑
                .userDetailsService(userService)
                // 指定存储位置
                .tokenRepository(tokenRepository)

            .and()

            // 自定义异常处理
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)

            .and()
            // 关闭csrf 防护
            .csrf().disable()
        ;

    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        // 设置数据源
        tokenRepository.setDataSource(dataSource);
        // 启动时是否创建表，第一次要，后面删除
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
