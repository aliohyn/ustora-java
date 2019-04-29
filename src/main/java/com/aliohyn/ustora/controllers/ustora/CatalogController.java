package com.aliohyn.ustora.controllers.ustora;

import com.aliohyn.ustora.model.Pagination;
import com.aliohyn.ustora.model.Product;
import com.aliohyn.ustora.repository.ProductRepository;
import com.aliohyn.ustora.service.ProductService;
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
public class CatalogController extends BaseUstoraController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    PaginationBuilder paginationBuilder;

    private static final int pageSize = 8;
    private static final int numPageAroundCurrent = 2;

    @RequestMapping("/catalog")
    public String catalogList(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Product> products = productService.getItemsByPage(page, pageSize);
        model.addAttribute("products", products);

        Pagination pagination = new Pagination();
        float allCount = productService.getCount().intValue();
        int pageCount = (int) Math.ceil(allCount / pageSize);
        List<String> paginationList = paginationBuilder.build(page, pageCount, numPageAroundCurrent);

        int itemsFrom = (page - 1) * pageSize + 1;
        int itemsTo =  itemsFrom + products.size() - 1;

        pagination.setPagination(paginationList);
        pagination.setAllItemsCount(productService.getCount().intValue());
        pagination.setShowItemsFrom(itemsFrom);
        pagination.setShowItemsTo(itemsTo);
        pagination.setCurPageNumber(page);

        model.addAttribute("pagination", pagination);
        return "ustora/catalog/list";
    }

    @GetMapping(value = "/catalog/{id}")
    public String catalogItemEdit(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).get();

        model.addAttribute("product", product);

        return "ustora/catalog/detail";
    }
}
