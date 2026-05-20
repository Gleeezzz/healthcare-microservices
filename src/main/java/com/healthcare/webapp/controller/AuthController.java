package com.healthcare.webapp.controller;

import com.healthcare.webapp.feign.UserClient;
import com.healthcare.webapp.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserModel user) {

        user.setRole("MEDECIN");
        userClient.create(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}