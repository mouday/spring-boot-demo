package com.example.demo.controller;

import com.example.demo.vo.EnumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    private Map<String, List<EnumVO>> enumConfig;

    @GetMapping("/")
    public String index() {
        return "Hello";
    }

    @GetMapping("/getEnumConfig")
    @ResponseBody
    public Map<String, List<EnumVO>> enumConfig() {
        return enumConfig;
    }
}
