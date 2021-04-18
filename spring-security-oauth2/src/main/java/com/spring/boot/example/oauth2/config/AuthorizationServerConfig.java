package com.spring.boot.example.oauth2.config;

import com.spring.boot.example.oauth2.service.DefaultUserDetailService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @ClassName AuthorizationServerConfig.java
 * @createTime 2021年04月14日 22:56:00
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private DefaultUserDetailService userDetailService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenStore redisTokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               // client Id
               .withClient("admin")
               // client 密码
               .secret(passwordEncoder.encode("123456"))
               // 重定向地址，获取授权码
               .redirectUris("http://www.baidu.com")
               // 授权范围
               .scopes("all")
               // 授权方式 授权码 密码
               .authorizedGrantTypes("authorization_code", "password");


    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            // 自定义登录逻辑
            .userDetailsService(userDetailService)
            // 授权管理器
            .authenticationManager(authenticationManager)
            // 令牌存储位置
            .tokenStore(redisTokenStore)
        ;
    }
}
