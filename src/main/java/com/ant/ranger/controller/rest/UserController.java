package com.ant.ranger.controller.rest;

import com.ant.ranger.data.CatalogData;
import com.ant.ranger.data.ResponseData;
import com.ant.ranger.data.TokenData;
import com.ant.ranger.data.wsdto.CatalogWsDTO;
import com.ant.ranger.data.wsdto.PageInfoWsDTO;
import com.ant.ranger.model.User;
import com.ant.ranger.repository.UserRepository;
import com.ant.ranger.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * @packgeName: com.ant.ranger.AntRanger.controller.rest
 * @ClassName: UserController
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/19-下午3:33
 * @version: 1.0
 * @since: JDK 1.8
 */
@EnableResourceServer
@RestController
public class UserController {


    @Value("${authHeader}")
    private String tokenHeader;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/user")
    @ResponseBody
    public String user() {
        return "hello,world";
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public String saveUser() {
        try {
            User user = new User();
            user.setName("ranger");
            user.setUid("admin");
            user.setPassword("hexinlei");
            user.setEmail("1015640878@qq.com");
            user.setImg("../ui/img/profile.jpg");
            user.setSelfIntroduction("Java Engineer");
            user.setNickname("AntRanger");
            user.setEnabled(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setAccountNonExpired(true);
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();

        }
    }


    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String tokenData = TokenUtils.createToken((UserDetails) auth.getPrincipal());
        response.setHeader(tokenHeader,tokenData);
        PrintWriter out = response.getWriter();
        out.write(tokenData);
        out.close();
        response.flushBuffer();
    }

    @RequestMapping(value = "/ajax/user/getPageInfo",method = RequestMethod.GET)
    @ResponseBody
    public Object getPageInfo()throws Exception{
        List<CatalogWsDTO> list = new LinkedList<>();
        CatalogWsDTO c1 = new CatalogWsDTO();
        c1.setName("商品管理");
        c1.setIndex("/home");
        list.add(c1);

        CatalogWsDTO c2 = new CatalogWsDTO();
        c2.setName("订单管理");
        c2.setIndex("/2");


        List<CatalogData> children2 = new LinkedList<>();
        CatalogWsDTO c21 = new CatalogWsDTO();
        c21.setName("B2C订单");
        c21.setIndex("/2-1/");
        c21.setParent(c2.getName());
        children2.add(c21);

        List<CatalogData> children21 = new LinkedList<>();
        CatalogWsDTO c211 = new CatalogWsDTO();
        c211.setName("B2C订单解锁");
        c211.setIndex("/2-1-1/");
        c211.setParent(c2.getName()+";"+c21.getName());
        children21.add(c211);

        CatalogWsDTO c212 = new CatalogWsDTO();
        c212.setName("B2C订单列表");
        c212.setIndex("/orderList");
        c212.setParent(c2.getName()+";"+c21.getName());
        children21.add(c212);
        c21.setChildren(children21);
        c2.setChildren(children2);
        list.add(c2);
        return new PageInfoWsDTO(list);
    }
}
