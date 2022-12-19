package com.example.demo.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/code")
    public void getCode(HttpSession session, HttpServletResponse response) throws IOException {
        // 利用 hutool 工具，生成验证码图片资源
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 5);

        // 获得生成的验证码字符
        String code = captcha.getCode();

        // 利用 session 来存储验证码
        session.setAttribute("code",code);

        // 将验证码图片的二进制数据写入【响应体 response 】
        captcha.write(response.getOutputStream());
    }


}