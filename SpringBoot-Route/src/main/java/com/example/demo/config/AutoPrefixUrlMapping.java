package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author coisini
 * @Description 自动补全路由前缀处理类
 * RequestMappingHandlerMapping 负责处理标注了@RequestMapping的控制器
 * @date Aug 10, 2021
 * @Version 1.0
 */
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    /**
     * 读取基础包配置
     */
    @Value("${api-package}")
    private String bathApiPackagePath;

    /**
     * 重写方法路由获取
     *
     * @param method
     * @param handlerType
     * @return
     */
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        if (Objects.nonNull(mappingInfo)) {
            String prefix = this.getPrefix(handlerType);
            if (StringUtils.hasText(prefix)) {
                mappingInfo = RequestMappingInfo
                        .paths(prefix)
                        .options(new RequestMappingInfo.BuilderConfiguration())
                        .build()
                        .combine(mappingInfo);
            }
        }

        return mappingInfo;
    }

    /**
     * 获取方法路由前缀
     *
     * @param handleType
     * @return
     */
    private String getPrefix(Class<?> handleType) {
        System.out.println("bathApiPackagePath:" + this.bathApiPackagePath);
        String packageName = handleType.getPackage().getName();
        System.out.println("packageName:" + packageName);
        String dotPath = packageName.replace(this.bathApiPackagePath, "").replace(".", "/") + "/**";
        System.out.println("dotPath:" + dotPath);
        return "/api/**";
    }

}
