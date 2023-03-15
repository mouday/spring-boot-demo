package com.example.demo.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * controller层统一使用该注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface ApiRestController {
    /**
     * Alias for {@link RestController#value}.
     */
    @AliasFor(annotation = RestController.class)
    String value() default "";
}
