package com.ant.ranger.service;

import com.ant.ranger.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.List;

/**
 * @packgeName: com.ant.ranger.service
 * @ClassName: UserService
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/19-下午5:17
 * @version: 1.0
 * @since: JDK 1.8
 */
public interface UserService extends ClientDetailsService{
    List<User> findAllUsers();
}
