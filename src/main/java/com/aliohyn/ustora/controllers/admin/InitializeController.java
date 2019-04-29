package com.aliohyn.ustora.controllers.admin;

import com.aliohyn.ustora.model.MenuItem;
import com.aliohyn.ustora.model.MenuType;
import com.aliohyn.ustora.repository.MenuItemRepository;
import com.aliohyn.ustora.repository.MenuTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class InitializeController {

    @Autowired
    public MenuTypeRepository menuTypeRepository;

    @Autowired
    public MenuItemRepository menuItemRepository;

    @RequestMapping("/admin/reinit")
    public String reinitialize() {
        MenuType menuType = menuTypeRepository.findByName("admin");

        if(menuType != null) {
            List<MenuItem> menuItemList = menuItemRepository.findAllByMenuTypeOrderByParentAsc(menuType);
            for(MenuItem item : menuItemList) {
                menuItemRepository.delete(item);
            }
            menuTypeRepository.delete(menuType);
        }

        menuType = new MenuType("admin", "Left menu in admin section");
        menuTypeRepository.save(menuType);
        menuItemRepository.save(new MenuItem("Dashboard", "/admin", menuType, 1, "fa-dashboard"));
        menuItemRepository.save(new MenuItem("Orders", "/admin/order", menuType, 3, "fa-shopping-bag"));
        menuItemRepository.save(new MenuItem("Users", "/admin/user", menuType, 4, "fa-user"));
        menuItemRepository.save(new MenuItem("Menu", "/admin/menu", menuType, 5, "fa-bars"));
        menuItemRepository.save(new MenuItem("Settings", "/admin/setting", menuType, 6, "fa-wrench"));

        MenuItem catalog = new MenuItem("Catalog", "/admin/catalog", menuType, 2, "fa-diamond");
        menuItemRepository.save(catalog);

        MenuItem product = new MenuItem("Product", "/admin/product", menuType, 1, "fa-circle");
        product.setParent(catalog);
        menuItemRepository.save(product);

        MenuItem brand = new MenuItem("Brand", "/admin/brand", menuType, 2, "fa-circle");
        brand.setParent(catalog);
        menuItemRepository.save(brand);

        MenuItem section = new MenuItem("Section", "/admin/section", menuType, 3, "fa-circle");
        section.setParent(catalog);
        menuItemRepository.save(section);

        MenuItem slider = new MenuItem("Slider", "/admin/slider", menuType, 4, "fa-circle");
        slider.setParent(catalog);
        menuItemRepository.save(slider);


        return "admin/dashboard";
    }
}
