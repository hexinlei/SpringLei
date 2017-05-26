package com.ant.ranger.repository;

import com.ant.ranger.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * @packgeName: com.ant.ranger.AntRanger.repository
 * @ClassName: UserRepository
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/19-下午4:03
 * @version: 1.0
 * @since: JDK 1.8
 */
@Repository
@Table(name = "user")
@Qualifier("userRepository")
public interface UserRepository extends CrudRepository<User,String>{

    @Query("select u from User u where u.uid =:uid")
    User findUserByUid(@Param("uid") String uid);
}
