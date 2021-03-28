package com.spring.boot.example.security.service;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 自定义权限认证接口实现 判断权限是否包含当前请求uri
 *
 * @ClassName MyAccessPermissionServiceImpl.java
 */
@Service
public class MyAccessPermissionServiceImpl implements MyAccessPermissionService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        System.out.println("===============MyAccessPermissionServiceImpl#hasPermission()==============");
        final String requestURI = request.getRequestURI();
        final Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            final Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            return authorities.contains(new SimpleGrantedAuthority(requestURI));
        }
        return false;
    }
}
