package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// @Controller
// @ResponseBody
@RestController
public class UserController {
    // @RequestMapping(value = "/users", method = RequestMethod.POST)
    @PostMapping("/users")
    public String save() {
        return "save";
    }

    //@RequestMapping(value = "/users", method = RequestMethod.PUT)
    @PutMapping("/users")
    public String update() {
        return "update";
    }

    //@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable Integer id) {
        return "delete id: " + id;
    }

    //@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @GetMapping("/users/{id}")
    public String get(@PathVariable Integer id) {
        return "get id: " + id;
    }

    //@RequestMapping(value = "/users", method = RequestMethod.GET)
    @GetMapping("/users")
    public String getList() {
        return "getList";
    }
}
