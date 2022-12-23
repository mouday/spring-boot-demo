package com.mouday.paymentdemo.controller;

import com.mouday.paymentdemo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin // 允许跨域访问
@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public Result test(){
        return Result.success().data("now", new Date());
    }
}
