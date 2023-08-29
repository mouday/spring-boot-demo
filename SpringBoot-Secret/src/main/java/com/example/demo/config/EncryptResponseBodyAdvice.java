package com.example.demo.config;

import com.example.demo.annotation.SecretData;
import com.example.demo.response.JsonResult;
import com.example.demo.response.JsonResultVO;
import com.example.demo.service.SecretDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

/**
 * 对响应内容加密
 */
@ControllerAdvice
@ConditionalOnProperty(name = "secret.enabled", havingValue = "true")
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private SecretDataService secretDataService;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().isAnnotationPresent(SecretData.class)
                || returnType.getMethod().getDeclaringClass().isAnnotationPresent(SecretData.class);
    }


    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("beforeBodyWrite");

        // 仅对JsonResult对象数据加密
        if (body instanceof JsonResult) {
            JsonResult jsonResult = (JsonResult) body;

            JsonResultVO jsonResultVO = new JsonResultVO();
            BeanUtils.copyProperties(jsonResult, jsonResultVO);

            String jsonStr = new ObjectMapper().writeValueAsString(jsonResult.getData());

            jsonResultVO.setEncryptData(secretDataService.encrypt(jsonStr));
            return jsonResultVO;
        } else {
            return body;
        }
    }
}
