package com.aliohyn.ustora.controllers.admin;

import com.aliohyn.ustora.model.Brand;
import com.aliohyn.ustora.model.Product;
import com.aliohyn.ustora.repository.BrandRepository;
import com.aliohyn.ustora.util.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class BrandController extends BaseAdminController {
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    FileSaver fileSaver;

    @RequestMapping(value = "/brand")
    public String catalogItemList(Model model) {
        model.addAttribute("brands", brandRepository.findAllByOrderByIdAsc());

        return "admin/brand/list";
    }


    @GetMapping("/brand/addBrand")
    public String catalogItemAdd(Model model) {
        model.addAttribute("action", "addBrand");

        return "admin/brand/edit";
    }


    @PostMapping("/brand/addBrand")
    public String catalogItemAddProcess(Brand brand, @RequestParam("file") MultipartFile file) throws IOException {
        if(file != null) {
            brand.setImage(fileSaver.save(file, "brand"));
        }
        brandRepository.save(brand);

        return "redirect:/admin/brand";
    }


    @GetMapping(value = "/brand/{id}")
    public String catalogItemEdit(@PathVariable Long id, Model model) {
        Brand brand = brandRepository.findById(id).get();

        model.addAttribute("brand", brand);
        model.addAttribute("action", "editBrand");

        return "admin/brand/edit";
    }

    @PostMapping(value = "/brand/editBrand")
    public String catalogItemEditProcess(Brand brand,
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam(value = "action", required = false) String action) throws IOException {

        if(action != null && action.equals("delete")) {
            brandRepository.delete(brand);
        } else {
            if(file != null) {
                brand.setImage(fileSaver.save(file, "brand"));
            }
            brandRepository.save(brand);
        }
        return "redirect:/admin/brand";
    }
}
