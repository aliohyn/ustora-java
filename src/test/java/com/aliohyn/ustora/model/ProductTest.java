package com.aliohyn.ustora.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductTest {
    @Test
    public void checkMethods() {
        Product item = new Product();

        item.setName("Name");
        assertThat(item.getName().equals("Name")).isTrue();

        item.setImage("Image");
        assertThat(item.getImage().equals("Image")).isTrue();

        item.setPrice(100.0);
        assertThat(item.getPrice() == 100.0).isTrue();

        item.setOldPrice(110.0);
        assertThat(item.getOldPrice() == 110.0).isTrue();

        item.setDescription("Description");
        assertThat(item.getDescription().equals("Description")).isTrue();

        item.setTopSellerShow(false);
        assertThat(item.getTopSellerShow()).isFalse();

        item.setNewShow(false);
        assertThat(item.getNewShow()).isFalse();

        Brand brand = new Brand("Name", "Path");
        item.setBrand(brand);
        assertThat(item.getBrand()).isEqualTo(brand);

        Section section = new Section("Section");
        item.setSection(section);
        assertThat(item.getSection()).isEqualTo(section);

        Product item2 = new Product();
        item2.setName("Name");
        item2.setImage("Image");
        item2.setPrice(100.0);
        item2.setOldPrice(110.0);
        item2.setDescription("Description");
        item2.setTopSellerShow(false);
        item2.setNewShow(false);
        Brand brand2 = new Brand("Name", "Path");
        item2.setBrand(brand2);
        Section section2 = new Section("Section");
        item2.setSection(section2);

        System.out.println(item);
        System.out.println(item2);
        assertThat(item.equals(item2)).isTrue();
        assertThat(item.hashCode()).isEqualTo(item2.hashCode());

        section2.setName("Name2");
        assertThat(item.equals(item2)).isFalse();
        assertThat(item.hashCode()).isNotEqualTo(item2.hashCode());
    }
}
