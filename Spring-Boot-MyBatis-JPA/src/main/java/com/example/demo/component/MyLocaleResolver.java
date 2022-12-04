package com.example.demo.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


/**
 * 区域信息解析器
 * 自定义国际化参数，支持链接上携带区域信息
 */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getParameter("lang");

        Locale locale = Locale.getDefault();

        if (!StringUtils.isEmpty(lang)) {
            String[] list = lang.split("_");

            if (list.length == 2) {
                locale = new Locale(list[0], list[1]);
            }
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
