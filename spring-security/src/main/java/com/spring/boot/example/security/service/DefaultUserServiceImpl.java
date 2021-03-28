package com.spring.boot.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName DefaultUserService.java
 */
@Service
public class DefaultUserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        final String encode = passwordEncoder.encode("123456");

        return new User("admin", encode,
                        AuthorityUtils.commaSeparatedStringToAuthorityList("admin,docker,sysoper,ROLE_root,/main.html"));
    }
}
