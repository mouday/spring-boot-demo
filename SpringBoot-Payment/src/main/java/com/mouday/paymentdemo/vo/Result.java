package com.mouday.paymentdemo.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class Result {

    // 响应码
    private Integer code;

    //响应消息
    private String message;

    //响应数据
    private Map<String, Object> data = new HashMap<>();

    public static Result success() {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("成功");
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(-1);
        result.setMessage("失败");
        return result;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
