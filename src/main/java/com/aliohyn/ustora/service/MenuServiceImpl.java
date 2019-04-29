package com.aliohyn.ustora.service;

import com.aliohyn.ustora.model.MenuItem;
import com.aliohyn.ustora.model.MenuType;
import com.aliohyn.ustora.repository.MenuItemRepository;
import com.aliohyn.ustora.repository.MenuTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuTypeRepository menuTypeRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    private List<MenuItem> menu;

    public List<MenuItem> getMenuItemsByType(String type) {
        MenuType menuType = menuTypeRepository.findByName(type);
        return menuItemRepository.findAllByMenuTypeOrderBySortAsc(menuType);
    }

    public List<MenuItem> buildMenu(String menuType) {
        List<MenuItem> allMenuItems = this.getMenuItemsByType(menuType);
        menu = new ArrayList<MenuItem>();

        for(MenuItem item : allMenuItems) {
            if(item.getParent() == null) {
                item.setDepthLevel(1);
                menu.add(item);
                this.insertChildren(item, 1, allMenuItems);
            }
        }

        return menu;
    }


    private void insertChildren(MenuItem parent, int depthLevel, List<MenuItem> allMenuItems) {
        // protect from unlimited recursion
        if(depthLevel < 10) {
            depthLevel++;
            for (MenuItem item : allMenuItems) {
                if (item.getParent() != null && item.getParent().equals(parent)) {
                    parent.setHasChildren(true);
                    item.setDepthLevel(depthLevel);
                    menu.add(item);
                    this.insertChildren(item, depthLevel, allMenuItems);
                }
            }
        } else {
            System.out.println("Error - unlimited recursion");
        }
    }
}
