package com.example.demofilterinterceptorlistener.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义过滤器
 *
 * 记录请求执行时间
 */
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter.doFilter-pre");
        long start = System.currentTimeMillis();

        chain.doFilter(request, response);

        long end =System.currentTimeMillis();
        System.out.println("MyFilter.doFilter-after time: " + (end - start) + " ms");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter.init");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter.destroy");
    }
}
