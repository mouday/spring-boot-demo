package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
    public Logger log;

    public BaseController() {
        this.log = LoggerFactory.getLogger(this.getClass());
    }
}
