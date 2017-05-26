package com.ant.ranger;

import com.ant.ranger.model.User;
import com.ant.ranger.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AntRangerApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void contextLoads() {
		User user = new User();
        user.setName("何鑫磊");
        user.setUid("admin1");
        user.setPassword("hexinlei");
        user.setEmail("1015640888@qq.com");
        user.setImg("../ui/img/profile.jpg");
        user.setSelfIntroduction("Java Engineer");
        user.setNickname("AntRanger");
        userRepository.save(user);
	}

}
