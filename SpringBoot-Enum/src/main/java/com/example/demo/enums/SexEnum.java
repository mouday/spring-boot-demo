package com.example.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 性别枚举
 */
public enum SexEnum implements IDictEnum {

    /**
     * 男性
     */
    MAN(1, "男性"),

    /**
     * 女性
     */
    WOMEN(2, "女性");

    /**
     * 存储值
     */
    @EnumValue // 标记数据库存的值是 code
    private final Integer code;

    /**
     * 显示值
     */
    private final String label;

    SexEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
