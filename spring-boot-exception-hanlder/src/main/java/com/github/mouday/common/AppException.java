package com.github.mouday.common;

public class AppException extends RuntimeException {
    private Integer code;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
