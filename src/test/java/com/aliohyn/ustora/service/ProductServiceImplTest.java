package com.aliohyn.ustora.service;

import com.aliohyn.ustora.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    ProductService productService;

    @Autowired
    EntityManager em;

    @Test
    public void getItemsByPage() {
        List<Product> products = productService.getItemsByPage(1, 2);
        assertThat(products.size()).isEqualTo(2);
        List<Product> products2 = productService.getItemsByPage(2, 2);
        assertThat(products.get(0).getId()).isNotEqualTo(products2.get(0).getId());
    }

    @Test
    public void getCount() {
        Long count = productService.getCount();
        assertThat(count).isGreaterThan(0);
    }
}

