package com.example.demo.response;

import lombok.Data;

/**
 * 统一的返回体数据 加密
 */
@Data
public class JsonResultVO {
    private String message;

    private String encryptData;

    private Integer code;
}
