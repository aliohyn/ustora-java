package com.aliohyn.ustora.controllers.admin;

import com.aliohyn.ustora.model.MenuItem;
import com.aliohyn.ustora.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class DashboardController extends BaseAdminController {
    @GetMapping
    public String dashboard(Model model, HttpServletRequest request) {

        return "admin/dashboard";
    }
}
