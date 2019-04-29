package com.aliohyn.ustora.controllers.ustora;

import com.aliohyn.ustora.model.CartLine;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BaseUstoraController {
    @ModelAttribute
    public void handleRequest(Model model, HttpSession session) {
        List<CartLine> cart;
        if(session.getAttribute("cart") != null) {
            cart = (List<CartLine>)session.getAttribute("cart");
        } else {
            cart = new ArrayList<>();
        }

        double cartSum = 0;
        int cartQuant = 0;
        for(CartLine item : cart) {
            cartSum += item.getProduct().getPrice();
            cartQuant += item.getQuantity();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("cartSum", cartSum);
        model.addAttribute("cartQuant", cartQuant);
    }
}
