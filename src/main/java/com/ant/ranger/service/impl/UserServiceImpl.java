package com.ant.ranger.service.impl;

import com.ant.ranger.bean.MyClientDetails;
import com.ant.ranger.bean.MyUserPrincipal;
import com.ant.ranger.model.User;
import com.ant.ranger.repository.UserRepository;
import com.ant.ranger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @packgeName: com.ant.ranger.service.impl
 * @ClassName: UserServiceImpl
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/19-下午5:18
 * @version: 1.0
 * @since: JDK 1.8
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userRepository.findUserByUid(s);
//        if (null == user) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        return new MyUserPrincipal(user);
//    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {

        User user = userRepository.findUserByUid(s);
        if (null == user) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new MyClientDetails(user);
    }
}
