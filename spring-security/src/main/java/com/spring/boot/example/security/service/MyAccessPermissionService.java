package com.spring.boot.example.security.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

/**
 * 自定义权限认证接口，给access使用
 *
 * @ClassName MyAccessPermissionService.java
 */
public interface MyAccessPermissionService {

    /**
     *
     * @param request
     * @param authentication 认证信息
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
