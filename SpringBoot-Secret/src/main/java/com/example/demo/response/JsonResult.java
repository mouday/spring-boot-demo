package com.example.demo.response;

import lombok.Data;

/**
 * 统一的返回体数据 不加密
 */
@Data
public class JsonResult<T> {
    private String message;

    private T data;

    private Integer code;

    public static <T> JsonResult success(T data){
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setCode(0);
        jsonResult.setData(data);
        jsonResult.setMessage("success");
        return jsonResult;
    }
}
