package com.aliohyn.ustora.controllers.ustora;

import com.aliohyn.ustora.model.CartLine;
import com.aliohyn.ustora.model.Product;
import com.aliohyn.ustora.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController extends BaseUstoraController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/cart")
    public String cart(Model model, HttpSession session) {
        return "ustora/cart/list";
    }

    @RequestMapping({"/cart/add/{id}/{quantity}", "/cart/add/{id}"})
    public String addToCart(@PathVariable Long id, @PathVariable(required = false) Integer quantity, Model model, HttpSession session) {
        if(quantity == null) {
            quantity = 1;
        }

        List<CartLine> cart;
        if(session.getAttribute("cart") != null) {
            cart = (List<CartLine>)session.getAttribute("cart");
        } else {
            cart = new ArrayList<>();
        }

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()) {
            CartLine cartLine = new CartLine(productOptional.get(), quantity);
            cart.add(cartLine);
            session.setAttribute("cart", cart);
        }

        return "redirect:/cart";
    }
}
