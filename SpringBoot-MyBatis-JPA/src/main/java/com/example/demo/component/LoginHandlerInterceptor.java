package com.example.demo.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Object loginUser = request.getSession().getAttribute("loginUser");
        // // 未登录，返回登录页面
        // if (loginUser == null) {
        //     request.setAttribute("msg", "没有权限，请先登录");
        //     request.getRequestDispatcher("/index.html").forward(request, response);
        //     return false;
        // }
        // // 已登录，放行
        // else {
        //     return true;
        // }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
