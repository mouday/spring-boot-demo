package com.mouday.demo.entity;

public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result(Object data, String msg, Integer code) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Object data) {
        this(data, "ok", 0);
    }

    public Result(Object data, String msg) {
        this(data, msg, 0);
    }

    public static Result success(Object data){
        return new Result(data);
    }

    public static Result error(Object data){
        return new Result(data, "error", -1);
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
