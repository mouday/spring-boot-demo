package com.example.demo.common;

/**
 * 统一的返回
 */
public class JsonResult {
    /**
     * 业务状态码
     */
    private Integer code = 0;

    /**
     * 提示消息
     */
    private String message = "";

    /**
     * 返回数据
     */
    private Object data = null;

    public static JsonResult success(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(data);
        return jsonResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
