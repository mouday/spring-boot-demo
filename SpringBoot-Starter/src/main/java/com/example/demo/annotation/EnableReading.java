package com.example.demo.annotation;


import com.example.demo.ReadingSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// @Import作用是往SpringIOC中导入一个类，这里即导入ReadingSelector
@Import(ReadingSelector.class)
public @interface EnableReading {

}