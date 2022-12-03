package com.github.mouday.controller;

import com.github.mouday.common.AppException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexController {

    /**
     * 首页
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "Hello Spring Boot";
    }

    /**
     * 运行时异常
     * @return
     */
    @GetMapping("/runtimeException")
    public String runtimeException() {
        throw new RuntimeException("服务端运行时异常");
    }

    /**
     * 业务异常
     * @return
     */
    @GetMapping("/appException")
    public String appException() {
        throw new AppException("服务端业务异常");
    }
}
