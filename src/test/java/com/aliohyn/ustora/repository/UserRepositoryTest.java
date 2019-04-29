package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.ProfileChecker;
import com.aliohyn.ustora.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileChecker profileChecker;

    @Test
    public void crud() {
        if(profileChecker.isDevProfile()) {
            // Create
            User item = new User();
            item.setUsername("Name");
            userRepository.save(item);
            Long id = item.getId();
            assertThat(id).isGreaterThan(0);

            // Read
            User item2 = userRepository.findById(id).get();
            assertThat(item2).isEqualTo(item2);

            // Update
            item2.setUsername("Updated");
            userRepository.save(item2);
            System.out.println(item2.getId());
            User item3 = userRepository.findById(item2.getId()).get();
            assertThat(item2).isEqualTo(item3);

            // Delete
            System.out.println(item3.getId());
            userRepository.delete(item3);
            assertThat(userRepository.findById(item3.getId()).isPresent()).isFalse();
        }
    }
}
