package com.example.demo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    // 授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);

        // 定制请求的授权规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/vip1/**").hasRole("VIP1")
                .antMatchers("/vip2/**").hasRole("VIP2")
                .antMatchers("/vip3/**").hasRole("VIP3");

        // 开启自动配置的登录
        http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("user-login");

        //开启注销
        http.logout();

        // 启用rememberMe功能，将用户信息保存在cookie中
        http.rememberMe();
    }

    // 认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        auth.inMemoryAuthentication()
                .withUser("vip1").password("{noop}123456").roles("VIP1")
                .and()
                .withUser("vip2").password("{noop}123456").roles("VIP2")
                .and()
                .withUser("vip3").password("{noop}123456").roles("VIP3");
    }
}
