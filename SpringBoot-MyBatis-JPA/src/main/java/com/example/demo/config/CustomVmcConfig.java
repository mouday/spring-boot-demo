package com.example.demo.config;

import com.example.demo.component.LoginHandlerInterceptor;
import com.example.demo.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 配置首页视图
 */

@Configuration
@SuppressWarnings("all")
public class CustomVmcConfig extends WebMvcConfigurerAdapter {
    // 设置首页位置，默认访问 public/index.html 没有经过模板引擎处理

    @Bean
    public WebMvcConfigurerAdapter CustomVmcConfig() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            /**
             * 注册视图控制器
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/dashboard.html").setViewName("dashboard");
            }

            /**
             * 注册拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // super.addInterceptors(registry);

                // 拦截任意路径下的所有请求, 排除请求
                // registry.addInterceptor(new LoginHandlerInterceptor())
                //         .addPathPatterns("/**")
                //         .excludePathPatterns("/index.html", "/", "/user/login", "/static/**", "/person/list");
            }
        };

        return adapter;
    }

    @Bean
    public MyLocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
