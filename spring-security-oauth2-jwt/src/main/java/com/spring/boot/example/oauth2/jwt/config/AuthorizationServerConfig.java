package com.spring.boot.example.oauth2.jwt.config;

import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @ClassName AuthorizationServerConfig.java
 * @createTime 2021年04月18日 11:48:00
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenStore jwtTokenStore;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private TokenEnhancerChain tokenEnhancerChain;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               // client Id
               .withClient("admin")
               // client 密码
               .secret(passwordEncoder.encode("123456"))
               // 访问令牌失效时间
               .accessTokenValiditySeconds(36000)
               // 刷新令牌失效时间
               .refreshTokenValiditySeconds(1)
               // 重定向地址，获取授权码
               .redirectUris("http://www.baidu.com")
               // 授权范围
               .scopes("all")
               // 授权方式 授权码 密码
               .authorizedGrantTypes("authorization_code", "password", "refresh_token");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 自定义登录逻辑
                .userDetailsService(userDetailsService)
                // 授权管理器
                .authenticationManager(authenticationManager)
                // jwt token
                .tokenStore(jwtTokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
        ;
    }
}
