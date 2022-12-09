package com.example.demojwt.intercepter;

import com.auth0.jwt.JWT;
import com.example.demojwt.annotation.PassToken;
import com.example.demojwt.entity.User;
import com.example.demojwt.service.UserService;
import com.example.demojwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * 拦截器去获取token并验证token
 * 默认所有接口都需要认证
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");

        // 1、如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 2、检查是否有 PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            return true;
        }

        // 3、检查 token
        if (token == null || "".equals(token)) {
            throw new RuntimeException("token无效");
        }

        // 4、验证 token
        if (!JwtUtil.verify(token)) {
            throw new RuntimeException("token验证失败");
        }

        // 5、验证用户是否存在
        // 获取 token 中的 userId
        Integer userId = JWT.decode(token).getClaim("userId").asInt();

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        return true;

    }
}
