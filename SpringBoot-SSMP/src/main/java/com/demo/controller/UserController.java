package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

// @Controller
// @ResponseBody
@RestController
@Slf4j
public class UserController{
    //private static final Logger log = LoggerFactory.getLogger(UserController.class);

    // @RequestMapping(value = "/users", method = RequestMethod.POST)
    @PostMapping("/users")
    public String save() {

        log.debug("debug");
        log.info("info"); // 默认级别
        log.warn("warn");
        log.error("error");
        log.error("error");
        log.error("error");
        log.error("error");
        log.error("error");
        log.error("error");
        log.error("error");

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
