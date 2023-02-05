package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    /**
     * 页面
     * @param model
     * @return
     */
    @GetMapping("/")
    public String websocket(Model model) {
        return "websocket";
    }
}
