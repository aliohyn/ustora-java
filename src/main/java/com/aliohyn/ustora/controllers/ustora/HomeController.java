package com.aliohyn.ustora.controllers.ustora;

import com.aliohyn.ustora.repository.BrandRepository;
import com.aliohyn.ustora.repository.ProductRepository;
import com.aliohyn.ustora.repository.SlideItemRepository;
import com.aliohyn.ustora.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseUstoraController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SlideItemRepository slideItemRepository;

    @Autowired
    BrandRepository brandRepository;

    @GetMapping("/")
    public String homePage(Model model) {

        model.addAttribute("sliderItems", slideItemRepository.findAll());
        model.addAttribute("latestProducts", productRepository.findByNewShow(true));
        model.addAttribute("brands", brandRepository.findAllByOrderByIdAsc());

        return "ustora/home";
    }
}
