package com.aliohyn.ustora.controllers.ustora;

import com.aliohyn.ustora.model.CartLine;
import com.aliohyn.ustora.model.Order;
import com.aliohyn.ustora.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController extends BaseUstoraController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/order")
    public String order() {
        return "ustora/order/form";
    }

    @PostMapping("/order")
    public String orderProcess(Order order, Model model, HttpSession session) {
        List<CartLine> cart = (List<CartLine>)session.getAttribute("cart");
        order.setCart(cart);
        orderRepository.save(order);

        model.addAttribute("order", order);
        if(order.getId() > 0) {
            return "ustora/order/complete";
        } else {
            return "ustora/order/form";
        }
    }
}
