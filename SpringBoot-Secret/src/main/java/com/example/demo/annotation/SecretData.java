package com.example.demo.annotation;


import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 只有添加有该注解的Controller类或是具体接口方法才进行数据的加密解密
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface SecretData {

}