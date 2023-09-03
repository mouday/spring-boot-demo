package com.example.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 颜色枚举
 */
public enum ColorEnum implements IDictEnum {
    /**
     * 红色
     */
    RED(1, "红色"),

    /**
     * 绿色
     */
    GREEN(2, "绿色"),

    /**
     * 蓝色
     */
    BLUE(3, "蓝色");

    /**
     * 存储值
     */
    @EnumValue // 标记数据库存的值是code
    private final Integer code;

    /**
     * 显示值
     */
    private final String label;

    ColorEnum(Integer code, String label) {
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
