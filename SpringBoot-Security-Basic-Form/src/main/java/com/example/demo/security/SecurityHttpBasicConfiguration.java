package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration
public class SecurityHttpBasicConfiguration {
    /**
     * 基于基础认证模式
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 禁用session会话
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 所有请求都需要认证，认证方式：httpBasic
        http.authorizeHttpRequests((auth) -> {
            auth.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

