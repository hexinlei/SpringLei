package com.ant.ranger.config;

import com.ant.ranger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import javax.annotation.Resource;

/**
 * @packgeName: com.ant.ranger.config
 * @ClassName: WebSecurityConfig
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/19-下午5:07
 * @version: 1.0
 * @since: JDK 1.8
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "myAccessDeniedServletHandler")
    private AccessDeniedServletHandler accessDeniedServletHandler;

    @Resource(name = "myAuthenticationEntryPoint")
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Value("${authHeader}")
    private String tokenHeader;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
//
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new AuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().cacheControl().disable();
        http.addFilterBefore(authenticationTokenFilterBean(),UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/login","/","/user","/saveUser").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().formLogin()
                .loginPage("/loginPage")
                .permitAll()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedServletHandler) // 登录过，但是没有权限
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint); //未登录

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
//                .passwordEncoder(passwordEncoder());
    }

    /**
     * 设置用户密码的加密方式为MD5加密
     * @return
     */
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

}
