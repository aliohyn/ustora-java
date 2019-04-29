package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.model.MenuItem;
import com.aliohyn.ustora.model.MenuType;
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
public class MenuItemRepositoryTest {
    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    MenuTypeRepository menuTypeRepository;

    @Test
    public void crud() {
        MenuType menuType = menuTypeRepository.findByName("admin");
        if(menuType == null) {
            menuType = new MenuType("admin", "Left menu in admin section");
            menuTypeRepository.save(menuType);
        }
        // Create
        MenuItem item = new MenuItem();
        item.setName("Dashboard");
        item.setLink("/");
        item.setDepthLevel(1);
        item.setIcon("test");
        item.setMenuType(menuType);

        menuItemRepository.save(item);
        Long id = item.getId();
        assertThat(id).isGreaterThan(0);

        MenuItem childItem = new MenuItem();
        childItem.setName("Dashboard");
        childItem.setLink("/");
        childItem.setDepthLevel(1);
        childItem.setIcon("test");
        childItem.setMenuType(menuType);
        childItem.setParent(item);
        menuItemRepository.save(childItem);

        // Read
        MenuItem item2 = menuItemRepository.findById(id).get();
        assertThat(item2).isEqualTo(item2);

        // Update
        item2.setName("Updated");
        menuItemRepository.save(item2);
        MenuItem item3 = menuItemRepository.findById(item2.getId()).get();
        assertThat(item2).isEqualTo(item3);

        // Delete
        menuItemRepository.delete(item3);
        menuItemRepository.delete(childItem);
        assertThat(menuItemRepository.findById(item3.getId()).isPresent()).isFalse();
    }
}
