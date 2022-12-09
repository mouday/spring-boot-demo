package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 返回json数据 支持GET POST
     * @return
     */
    @RequestMapping(value="/json", method={RequestMethod.GET, RequestMethod.POST})
    public Object json() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 23);

        return map;
    }


}
