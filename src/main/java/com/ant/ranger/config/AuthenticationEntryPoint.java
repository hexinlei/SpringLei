package com.ant.ranger.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @packgeName: com.ant.ranger.config
 * @ClassName: AuthenticationEntryPoint
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<未登录验证>
 * @author: hexinlei
 * @date: 2017/5/22-上午11:36
 * @version: 1.0
 * @since: JDK 1.8
 */
@Component(value = "myAuthenticationEntryPoint")
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}

