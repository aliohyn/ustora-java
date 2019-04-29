package com.aliohyn.ustora.service;

import com.aliohyn.ustora.model.MenuItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceImplTest {
    @Autowired
    MenuService menuService;

    @Test
    public void buildMenu() {
        List<MenuItem> adminMenu = menuService.buildMenu("admin");
        MenuItem prevItem = null;
        for(MenuItem item : adminMenu) {
            if(prevItem != null && prevItem.getDepthLevel() < item.getDepthLevel()) {
                assertThat(prevItem.getId()).isEqualTo(item.getParent().getId());
                assertThat(prevItem.getHasChildren()).isTrue();
            }
            prevItem = item;
        }
    }
}
