package com.example.demo.enums;

/**
 * 字典枚举接口
 */
public interface IDictEnum {
    Integer getCode();

    String getLabel();

    String name();

    // @JsonValue // 标记响应json值
    default String getValue() {
        return this.name();
    }
}

