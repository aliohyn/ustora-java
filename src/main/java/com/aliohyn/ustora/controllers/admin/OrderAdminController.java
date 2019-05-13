package com.aliohyn.ustora.controllers.admin;

import com.aliohyn.ustora.model.Order;
import com.aliohyn.ustora.model.Pagination;
import com.aliohyn.ustora.service.OrderService;
import com.aliohyn.ustora.util.PaginationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderAdminController extends BaseAdminController {

    @Autowired
    OrderService orderService;

    @Autowired
    PaginationBuilder paginationBuilder;

    private static final int pageSize = 20;
    private static final int numPageAroundCurrent = 2;

    @RequestMapping(value = "/order")
    public String orderList(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Order> orders = orderService.getItemsByPage(page, pageSize);
        model.addAttribute("orders", orders);

        Pagination pagination = new Pagination();
        float allCount = orderService.getCount().intValue();
        int pageCount = (int) Math.ceil(allCount / pageSize);
        List<String> paginationList = paginationBuilder.build(page, pageCount, numPageAroundCurrent);

        int itemsFrom = (page - 1) * pageSize + 1;
        int itemsTo =  itemsFrom + orders.size() - 1;

        pagination.setPagination(paginationList);
        pagination.setAllItemsCount(orderService.getCount().intValue());
        pagination.setShowItemsFrom(itemsFrom);
        pagination.setShowItemsTo(itemsTo);
        pagination.setCurPageNumber(page);

        model.addAttribute("pagination", pagination);

        return "admin/order/list";
    }

    @GetMapping(value = "/order/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
        Order order = orderService.getItemById(id);
        model.addAttribute("order", order);
        return "admin/order/detail";
    }
}
