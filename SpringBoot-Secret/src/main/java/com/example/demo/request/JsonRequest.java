package com.example.demo.request;

import lombok.Data;

/**
 * 统一的请求体数据
 */
@Data
public class JsonRequest {
    /**
     * 未加密数据
     */
    private Object data;
    /**
     * 加密数据
     */
    private String encryptData;
}
