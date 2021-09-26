package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FreemarkerController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Tom");
        return "index";
    }
}
