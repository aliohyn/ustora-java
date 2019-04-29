package com.aliohyn.ustora.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SectionTest {
    @Test
    public void checkMethods() {
        Section item = new Section();

        item.setName("Name");
        assertThat(item.getName().equals("Name")).isTrue();

        Section item2 = new Section();
        item2.setName("Name");
        assertThat(item.equals(item2)).isTrue();
        assertThat(item.hashCode()).isEqualTo(item2.hashCode());

        item2.setName("Name2");
        assertThat(item.equals(item2)).isFalse();
        assertThat(item.hashCode()).isNotEqualTo(item2.hashCode());
    }
}
