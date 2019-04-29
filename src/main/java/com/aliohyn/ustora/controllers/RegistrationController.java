package com.aliohyn.ustora.controllers;

import com.aliohyn.ustora.repository.UserRepository;
import com.aliohyn.ustora.model.UserRegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(UserRegistrationForm form, Model model) {
        try {
            userRepo.save(form.toUser(passwordEncoder));
        } catch (Exception e) {
            model.addAttribute("message", "Error, this user already exist or error parameters");
            return "registration";
        }
        return "redirect:/login";
    }
}
