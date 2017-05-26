package com.ant.ranger.config;

import com.ant.ranger.bean.MyUserPrincipal;
import com.ant.ranger.service.UserService;
import com.ant.ranger.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @packgeName: com.ant.ranger.config
 * @ClassName: AuthenticationTokenFilter
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/22-下午12:10
 * @version: 1.0
 * @since: JDK 1.8
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter{

    @Value("${authHeader}")
    private String tokenHeader;

    @Resource
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static List<String> exceptionUrls = new LinkedList<>();
    static {
        exceptionUrls.add("/login");
        exceptionUrls.add("/");
        exceptionUrls.add("/user");
        exceptionUrls.add("/saveUser");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException,UsernameNotFoundException {

        String authHeader = httpServletRequest.getHeader(this.tokenHeader);

        String url = httpServletRequest.getRequestURI();

        if (!exceptionUrls.contains(url)){
            if (StringUtils.isNotEmpty(authHeader)) {
                String uid = TokenUtils.getUserNameFromToken(authHeader);
                MyUserPrincipal userDetail = (MyUserPrincipal) userService.loadUserByUsername(uid);

                if (!TokenUtils.validateToken(authHeader,userDetail)) {
                    throw new UsernameNotFoundException("Token 验证不通过");
                }else {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(uid, null);
                    Authentication auth = authenticationManager.authenticate(token);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }else {
                throw new UsernameNotFoundException("Token 验证不通过");
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

}
