package com.aliohyn.ustora.controller.admin;

import com.aliohyn.ustora.controllers.ustora.HomeController;
import com.aliohyn.ustora.model.MenuItem;
import com.aliohyn.ustora.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DashboardControllerTest {
    @Autowired
    MenuService menuService;

    @Test
    public void testDashboardPage() throws Exception {
        String currentUrl = "/admin";

        ArrayList<MenuItem> adminMenu = (ArrayList<MenuItem>) menuService.getMenuItemsByType("admin");
        adminMenu.get(0).setSelected(true);
        assertThat(adminMenu.get(0).getSelected()).isTrue();
    }
}
