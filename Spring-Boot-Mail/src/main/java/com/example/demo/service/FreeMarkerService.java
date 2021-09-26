package com.example.demo.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Map;

@Service
public class FreeMarkerService {
    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;

    public String renderTemplate(String templateName, Map<String, Object> model) throws IOException, TemplateException {
        Template template = configurer.getConfiguration().getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}
