package com.aliohyn.ustora.controllers.admin;

import com.aliohyn.ustora.model.Pagination;
import com.aliohyn.ustora.model.Product;
import com.aliohyn.ustora.repository.BrandRepository;
import com.aliohyn.ustora.repository.ProductRepository;
import com.aliohyn.ustora.repository.SectionRepository;
import com.aliohyn.ustora.service.ProductService;
import com.aliohyn.ustora.util.FileSaver;
import com.aliohyn.ustora.util.PaginationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController extends BaseAdminController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FileSaver fileSaver;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    PaginationBuilder paginationBuilder;

    private static final int pageSize = 20;
    private static final int numPageAroundCurrent = 2;

    @RequestMapping(value = "/product")
    public String catalogItemList(@RequestParam(defaultValue = "1") int page, Model model) {
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

        return "admin/product/list";
    }

    @GetMapping("/product/addProduct")
    public String catalogItemAdd(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("sections", sectionRepository.findAll());
        model.addAttribute("action", "addProduct");

        return "admin/product/edit";
    }

    @PostMapping("/product/addProduct")
    public String catalogItemAddProcess(Product product, @RequestParam("file") MultipartFile file) throws IOException {
        if(file != null) {
            product.setImage(fileSaver.save(file, "product"));
        }
        productRepository.save(product);

        return "redirect:/admin/product";
    }


    @GetMapping(value = "/product/{id}")
    public String catalogItemEdit(@PathVariable Long id, Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("sections", sectionRepository.findAll());
        Product product = productRepository.findById(id).get();

        model.addAttribute("product", product);
        model.addAttribute("action", "editProduct");

        return "admin/product/edit";
    }

    @PostMapping(value = "/product/editProduct")
    public String catalogItemEditProcess(Product product,
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam(value = "action", required = false) String action) throws IOException {

        if(action != null && action.equals("delete")) {
            productRepository.delete(product);
        } else {
            if(!file.isEmpty()) {
                product.setImage(fileSaver.save(file, "product"));
            }
            productRepository.save(product);
        }
        return "redirect:/admin/product";
    }
}
