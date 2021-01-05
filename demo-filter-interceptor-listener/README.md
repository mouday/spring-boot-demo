# demo-filter-interceptor-listener

拦截器、过滤器、监听器

##  过滤器

```java
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

```

## 拦截器
```java
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

```

注册生效
```java
package com.example.demofilterinterceptorlistener.config;

import com.example.demofilterinterceptorlistener.filter.MyFilter;
import com.example.demofilterinterceptorlistener.interceptor.MyInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**");
    }

    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}

```

执行结果
```
MyFilter.init
...
MyFilter.doFilter-pre
MyInterceptor.preHandle
MyInterceptor.postHandle
MyInterceptor.afterCompletion: http://localhost:8080/ duration: 26ms
MyFilter.doFilter-after time: 33 ms
...
MyFilter.destroy
```
>参考
> [Spring Boot 如何使用拦截器、过滤器、监听器？](https://zhuanlan.zhihu.com/p/257104137)