package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class FreemarkerController {
    @GetMapping("/")
    public String index(Model model) {

        List<Object> list = new ArrayList<>();
        list.add(new HashMap<String, Object>() {{
            put("name", "Tom");
            put("age", 23);
        }});
        list.add(new HashMap<String, Object>() {{
            put("name", "Jack");
            put("age", 24);
        }});

        model.addAttribute("list", list);
        model.addAttribute("name", "Tom");

        return "index";
    }
}
