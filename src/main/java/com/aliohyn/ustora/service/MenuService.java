package com.aliohyn.ustora.service;

import com.aliohyn.ustora.model.MenuItem;
import java.util.List;

public interface MenuService {
    List<MenuItem> getMenuItemsByType(String type);
    List<MenuItem> buildMenu(String menuType);
}