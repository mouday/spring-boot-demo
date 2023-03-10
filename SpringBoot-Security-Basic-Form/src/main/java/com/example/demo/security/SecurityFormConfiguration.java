package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFormConfiguration {
    /**
     * 基于表单认证模式
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 启用session会话
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        // 认证方式：Form
        http.authorizeRequests()
                // 所有请求都需要认证
                .anyRequest().authenticated()
                .and()
                // 启动表单认证模式
                .formLogin()
                // 登录页面
                .loginPage("/login.html")
                // 请求提交地址
                .loginProcessingUrl("/login")
                // 放行上面的两个地址
                .permitAll()
                // 设置提交的参数名
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                // 开始设置注销功能
                .logout()
                // 注销的url
                .logoutUrl("/logout")
                // session直接过期
                .invalidateHttpSession(true)
                // 清除认证信息
                .clearAuthentication(true)
                // 注销成功后跳转地址
                .logoutSuccessUrl("/login.html")
                .and()
                // 禁用csrf安全防护
                .csrf().disable();

        return http.build();
    }
}

