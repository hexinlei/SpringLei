package com.ant.ranger.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @packgeName: com.ant.ranger.config
 * @ClassName: AccessDeniedServletHandler
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<登录却没有权限>
 * @author: hexinlei
 * @date: 2017/5/23-下午12:49
 * @version: 1.0
 * @since: JDK 1.8
 */
@Component(value = "myAccessDeniedServletHandler")
public class AccessDeniedServletHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletRequest.getHeaderNames();
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
