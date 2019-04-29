package com.aliohyn.ustora.controllers.admin;

import com.aliohyn.ustora.model.SlideItem;
import com.aliohyn.ustora.repository.SlideItemRepository;
import com.aliohyn.ustora.util.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class SliderController extends BaseAdminController {
    @Autowired
    SlideItemRepository slideItemRepository;

    @Autowired
    FileSaver fileSaver;

    @RequestMapping(value = "/slider")
    public String catalogItemList(Model model) {
        model.addAttribute("slider", slideItemRepository.findAllByOrderByIdAsc());
        return "admin/slider/list";
    }


    @GetMapping("/slider/addSlideItem")
    public String catalogItemAdd(Model model) {
        model.addAttribute("action", "addSlideItem");

        return "admin/slider/edit";
    }


    @PostMapping("/slider/addSlideItem")
    public String catalogItemAddProcess(SlideItem slideItem, @RequestParam("file") MultipartFile file) throws IOException {
        if(file != null) {
            slideItem.setImage(fileSaver.save(file, "slider"));
        }
        slideItemRepository.save(slideItem);

        return "redirect:/admin/slider";
    }


    @GetMapping(value = "/slider/{id}")
    public String catalogItemEdit(@PathVariable Long id, Model model) {
        SlideItem slideItem = slideItemRepository.findById(id).get();

        model.addAttribute("slideItem", slideItem);
        model.addAttribute("action", "editSlideItem");

        return "admin/slider/edit";
    }

    @PostMapping(value = "/slider/editSlideItem")
    public String catalogItemEditProcess(SlideItem slideItem,
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam(value = "action", required = false) String action) throws IOException {

        if(action != null && action.equals("delete")) {
            slideItemRepository.delete(slideItem);
        } else {
            if(file != null) {
                slideItem.setImage(fileSaver.save(file, "slider"));
            }
            slideItemRepository.save(slideItem);
        }
        return "redirect:/admin/slider";
    }
}
