package com.aliohyn.ustora.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaginationBuilderTest {
    @Autowired
    PaginationBuilder paginationBuilder;

    @Test
    public void paginationBuild() {
        List<String> paginationRight1 = Arrays.asList("1", "2", "3", "...", "20");
        List<String> pagination1 = paginationBuilder.build(1, 20, 2);
        assertThat(pagination1).isEqualTo(paginationRight1);

        List<String> paginationRight2 = Arrays.asList("1", "2", "3", "4", "5", "...", "20");
        List<String> pagination2 = paginationBuilder.build(3, 20, 2);
        assertThat(pagination2).isEqualTo(paginationRight2);

        List<String> paginationRight3 = Arrays.asList("1", "...", "3", "4", "5", "6", "7", "...", "10");
        List<String> pagination3 = paginationBuilder.build(5, 10, 2);
        assertThat(pagination3).isEqualTo(paginationRight3);

        List<String> paginationRight4 = Arrays.asList("1", "...", "16", "17", "18", "19", "20");
        List<String> pagination4 = paginationBuilder.build(20, 20, 4);
        assertThat(pagination4).isEqualTo(paginationRight4);

        List<String> paginationRight5 = Arrays.asList("1", "2", "3", "4");
        List<String> pagination5 = paginationBuilder.build(1, 4, 4);
        List<String> pagination6 = paginationBuilder.build(2, 4, 4);
        List<String> pagination7 = paginationBuilder.build(4, 4, 4);

        assertThat(pagination5).isEqualTo(paginationRight5);
        assertThat(pagination6).isEqualTo(paginationRight5);
        assertThat(pagination7).isEqualTo(paginationRight5);

        List<String> paginationRight8 = Arrays.asList("1", "2", "3", "4", "5");
        List<String> pagination8 = paginationBuilder.build(1, 5, 3);
        List<String> pagination9 = paginationBuilder.build(3, 5, 3);
        List<String> pagination10 = paginationBuilder.build(4, 5, 3);

        assertThat(pagination8).isEqualTo(paginationRight8);
        assertThat(pagination9).isEqualTo(paginationRight8);
        assertThat(pagination10).isEqualTo(paginationRight8);

        List<String> paginationRight11 = Arrays.asList("1", "2", "3", "4", "5");
        List<String> pagination11 = paginationBuilder.build(1, 5, 6);
        List<String> pagination12 = paginationBuilder.build(2, 5, 6);
        List<String> pagination13 = paginationBuilder.build(4, 5, 6);

        assertThat(pagination11).isEqualTo(paginationRight11);
        assertThat(pagination12).isEqualTo(paginationRight11);
        assertThat(pagination13).isEqualTo(paginationRight11);

        List<String> paginationRight14 = Arrays.asList("1", "...", "3", "4", "5");
        List<String> pagination14 = paginationBuilder.build(5, 5, 2);
        assertThat(pagination14).isEqualTo(paginationRight14);

        List<String> paginationRight15 = Arrays.asList("1", "2", "3", "4", "5");
        List<String> pagination15 = paginationBuilder.build(3, 5, 2);
        assertThat(pagination15).isEqualTo(paginationRight15);

        List<String> paginationRight16 = Arrays.asList("1", "2", "3", "...", "5");
        List<String> pagination16 = paginationBuilder.build(2, 5, 1);
        assertThat(pagination16).isEqualTo(paginationRight16);

        List<String> paginationRight17 = Arrays.asList("1", "2", "3", "4");
        List<String> pagination17 = paginationBuilder.build(4, 4, 3);
        assertThat(pagination17).isEqualTo(paginationRight17);

        List<String> paginationRight18 = Arrays.asList("1", "2", "3", "4", "5");
        List<String> pagination18 = paginationBuilder.build(4, 5, 3);
        assertThat(pagination18).isEqualTo(paginationRight18);

        List<String> paginationRight19 = Arrays.asList("1", "2", "3", "4", "5");
        List<String> pagination19 = paginationBuilder.build(3, 5, 1);
        assertThat(pagination19).isEqualTo(paginationRight19);
    }
}
