package com.github.mouday.common;

import java.io.Serializable;

public class JsonResult implements Serializable {

    private String msg;

    private Integer code;

    private Object data;


    private JsonResult(Object data, String msg, Integer code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }


    public static JsonResult success(Object data) {
        return new JsonResult(data, "success", 0);
    }

    public static JsonResult error() {
        return new JsonResult(null, "error", -1);
    }

    public static JsonResult error(String msg) {
        return new JsonResult(null, msg, -1);
    }

    public static JsonResult error(String msg, Integer code) {
        return new JsonResult(null, msg, code);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
