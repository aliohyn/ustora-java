package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.ProfileChecker;
import com.aliohyn.ustora.model.Brand;
import com.aliohyn.ustora.model.Product;
import com.aliohyn.ustora.model.Section;
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
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProfileChecker profileChecker;

    @Test
    public void crud() {
        if(profileChecker.isDevProfile()) {
            // Create
            Product item = new Product();
            item.setName("Canon camera");
            item.setDescription("Description");
            item.setPrice(200);
            item.setNewShow(true);
            item.setTopSellerShow(true);
            item.setBrand(new Brand("Canon", "/img/brand2.png"));
            item.setSection(new Section("Camera"));
            productRepository.save(item);
            Long id = item.getId();
            assertThat(id).isGreaterThan(0);

            // Read
            Product item2 = productRepository.findById(id).get();
            assertThat(item2).isEqualTo(item2);

            // Update
            item2.setName("Updated");
            productRepository.save(item2);
            Product item3 = productRepository.findById(item2.getId()).get();
            assertThat(item2).isEqualTo(item3);

            // Delete
            productRepository.delete(item3);
            assertThat(productRepository.findById(item3.getId()).isPresent()).isFalse();
        }
    }
}
