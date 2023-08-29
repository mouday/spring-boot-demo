package com.example.demo.config;

import com.example.demo.annotation.SecretData;
import com.example.demo.request.JsonRequest;
import com.example.demo.service.SecretDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 对请求内容进行解密
 * 只有开启了加解密功能才会生效
 * 仅对使用了@RqestBody注解的生效
 * https://blog.csdn.net/xingbaozhen1210/article/details/98189562
 */
@ControllerAdvice
// @ConditionalOnProperty(name = "secret.enabled", havingValue = "true")
public class DecryptRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Resource
    private SecretDataService secretDataService;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getMethod().isAnnotationPresent(SecretData.class)
                || methodParameter.getMethod().getDeclaringClass().isAnnotationPresent(SecretData.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        System.out.println("beforeBodyRead");

        String body = inToString(inputMessage.getBody());
        System.out.println(body);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonRequest jsonRequest = objectMapper.readValue(body, JsonRequest.class);

        // 默认取data数据，如果提交加密数据则解密
        String decryptData = null;
        if (jsonRequest.getEncryptData() != null) {
            decryptData = secretDataService.decrypt(jsonRequest.getEncryptData());
        } else{
            decryptData = objectMapper.writeValueAsString(jsonRequest.getData());
        }

        String data = decryptData;

        // 解密后的数据
        System.out.println(data);

        return new HttpInputMessage() {
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(data.getBytes());
            }
        };
    }

    /**
     * 读取输入流为字符串
     *
     * @param is
     * @return
     */
    private String inToString(InputStream is) {
        byte[] buf = new byte[10 * 1024];
        int length = -1;
        StringBuilder sb = new StringBuilder();
        try {
            while ((length = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, length));
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}