package com.spring.boot.example.oauth2.jwt.service;

import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName DefaultUserService.java
 * @createTime 2021年04月18日 11:49:00
 */
@Service
public class DefaultUserService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("admin", passwordEncoder.encode("123456"),
                        AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
