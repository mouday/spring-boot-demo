package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class ThymeleafService {
    @Autowired
    TemplateEngine templateEngine;

    public String renderTemplate(String templateName, Map<String, Object> model){
        Context content = new Context();

        for (String key : model.keySet()) {
            content.setVariable(key, model.get(key));
        }

        return templateEngine.process(templateName, content);
    }
}
