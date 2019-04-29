package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.ProfileChecker;
import com.aliohyn.ustora.model.Brand;
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
public class BrandRepositoryTest {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProfileChecker profileChecker;


    @Test
    public void crud() {
        // Create
        Brand item = new Brand();
        item.setName("Name");
        item.setImage("Image");
        brandRepository.save(item);
        Long id = item.getId();
        assertThat(id).isGreaterThan(0);

        // Read
        Brand item2 = brandRepository.findById(id).get();
        assertThat(item2).isEqualTo(item2);

        // Update
        item2.setName("Updated");
        brandRepository.save(item2);
        Brand item3 = brandRepository.findById(item2.getId()).get();
        assertThat(item2).isEqualTo(item3);

        // Delete
        brandRepository.delete(item3);
        assertThat(brandRepository.findById(item3.getId()).isPresent()).isFalse();
    }
}
