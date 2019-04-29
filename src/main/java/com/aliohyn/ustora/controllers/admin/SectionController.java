package com.aliohyn.ustora.controllers.admin;

import com.aliohyn.ustora.model.Section;
import com.aliohyn.ustora.repository.SectionRepository;
import com.aliohyn.ustora.util.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class SectionController extends BaseAdminController {
    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    FileSaver fileSaver;

    @RequestMapping(value = "/section")
    public String catalogItemList(Model model) {
        model.addAttribute("sections", sectionRepository.findAllByOrderByIdAsc());
        return "admin/section/list";
    }


    @GetMapping("/section/addSection")
    public String catalogItemAdd(Model model) {
        model.addAttribute("action", "addSection");

        return "admin/section/edit";
    }


    @PostMapping("/section/addSection")
    public String catalogItemAddProcess(Section section, @RequestParam("file") MultipartFile file) throws IOException {
        sectionRepository.save(section);

        return "redirect:/admin/section";
    }


    @GetMapping(value = "/section/{id}")
    public String catalogItemEdit(@PathVariable Long id, Model model) {
        Section section = sectionRepository.findById(id).get();

        model.addAttribute("section", section);
        model.addAttribute("action", "editSection");

        return "admin/section/edit";
    }

    @PostMapping(value = "/section/editSection")
    public String catalogItemEditProcess(Section section,
                                         @RequestParam(value = "action", required = false) String action) throws IOException {

        if(action != null && action.equals("delete")) {
            sectionRepository.delete(section);
        } else {
            sectionRepository.save(section);
        }
        return "redirect:/admin/section";
    }
}
