package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;


@Controller
public class IndexController {

    @RequestMapping("/hello")
    public String hello(HashMap<String, Object> map){
        map.put("name", "Tom");
        map.put("pets", Arrays.asList("dog", "cat", "pig"));

        // 模板路径
        // src/main/resources/templates/about.html

        System.out.println("444");

        return "hello";
    }

}
