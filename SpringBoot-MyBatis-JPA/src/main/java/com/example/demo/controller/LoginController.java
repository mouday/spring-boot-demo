package com.example.demo.controller;

import com.example.demo.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @PostMapping("/user/login")
    // 等价于 @RequestMapping(value = "/user/login", method = {RequestMethod.POST})
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session
    ) {

        if(StringUtils.isEmpty(username)){
            throw new UserNotExistException();
        }

        if("123".equals(username)){
            throw new UserNotExistException();
        }

        if ("123".equals(password)) {
            session.setAttribute("loginUser", username);
            // 登录成功 防止表单重新提交，做一个重定向
            return "redirect:/dashboard.html";
        } else {
            // 登录失败
            map.put("msg", "账号或密码不正确");
            return "login";
        }
    }
}
