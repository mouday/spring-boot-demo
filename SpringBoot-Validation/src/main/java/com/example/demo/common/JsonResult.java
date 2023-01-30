package com.example.demo.common;


/**
 * 统一的数据返回
 */
public class JsonResult {
    private Integer code;
    private String msg;
    private Object data;

    public JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult success(Object data){
        return new JsonResult(0, "success", data);
    }

    public static JsonResult error(String errorMessage) {
        return new JsonResult(-1, errorMessage, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

