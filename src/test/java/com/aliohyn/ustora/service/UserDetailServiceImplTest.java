package com.aliohyn.ustora.service;

import com.aliohyn.ustora.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDetailServiceImplTest {
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        user.setCity("city");
        user.setFullName("fullName");
        user.setStreet("street");
        user.setPhoneNumber("phone");
        user.setZip("zip");
        userDetailService.addUser(user);
        User user2 = (User) userDetailService.loadUserByUsername("username");

        assertThat(user2.equals(user)).isTrue();
    }
}
