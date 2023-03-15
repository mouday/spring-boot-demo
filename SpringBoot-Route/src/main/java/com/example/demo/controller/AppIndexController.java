package com.example.demo.controller;

import com.example.demo.config.RouteConfig;
import com.example.demo.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppIndexController {
    @Autowired
    private RouteConfig routeConfig;

    @GetMapping("/route")
    public List<Route> route() {
        return routeConfig.getAllRouteMapping();
    }
}
