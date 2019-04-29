package com.aliohyn.ustora.controllers.admin;

import com.aliohyn.ustora.model.MenuItem;
import com.aliohyn.ustora.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseAdminController {
    @Autowired
    MenuService menuService;


    @ModelAttribute
    public void handleRequest(Model model, HttpServletRequest request) {
        List<MenuItem> adminMenu = menuService.buildMenu("admin");
        String currentUrl = request.getServletPath();

        for (MenuItem item : adminMenu) {
            if (currentUrl.equals(item.getLink())) {
                item.setSelected(true);
            }
        }

        model.addAttribute("adminMenu", adminMenu);
    }
}
