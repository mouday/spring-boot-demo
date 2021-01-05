package com.example.demofilterinterceptorlistener.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * 统计请求处理时间
 */
public class MyInterceptor implements HandlerInterceptor {

    // 在 Controller 处理请求之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor.preHandle");
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    // 在 Controller 处理请求执行完成后、生成视图前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor.postHandle");
    }

    // 在 DispatcherServlet 完全处理请求后被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long)request.getAttribute("startTime");
        long endTime =  System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("MyInterceptor.afterCompletion: " + request.getRequestURL() +" duration: " + duration + "ms");
    }
}
