package com.mouday.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * 不使用模板引擎，templates
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index.html";
    }
}
