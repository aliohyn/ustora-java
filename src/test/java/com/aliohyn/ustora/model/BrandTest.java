package com.aliohyn.ustora.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BrandTest {
    @Test
    public void checkMethods() {
        Brand item = new Brand();

        item.setName("Name");
        assertThat(item.getName().equals("Name")).isTrue();
        item.setImage("Image");
        assertThat(item.getImage().equals("Image")).isTrue();

        Brand item2 = new Brand();
        item2.setName("Name");
        item2.setImage("Image");
        assertThat(item.equals(item2)).isTrue();
        assertThat(item.hashCode()).isEqualTo(item2.hashCode());

        item2.setName("Name2");
        assertThat(item.equals(item2)).isFalse();
        assertThat(item.hashCode()).isNotEqualTo(item2.hashCode());
    }
}
