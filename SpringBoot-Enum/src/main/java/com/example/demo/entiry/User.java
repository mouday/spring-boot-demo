package com.example.demo.entiry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.enums.ColorEnum;
import com.example.demo.enums.SexEnum;
import lombok.Data;

/**
 * 用户表
 */
@Data
@TableName("tb_user")
public class User {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private SexEnum sex;

    /**
     * 颜色
     */
    private ColorEnum color;

    /**
     * 增加一个label字段，便于前端展示数据
     */
    @TableField(exist = false)
    private String sexLabel;

    public String getSexLabel() {
        if (this.sex == null) {
            return "";
        }

        return this.sex.getLabel();
    }

    /**
     * 增加一个label字段，便于前端展示数据
     */
    @TableField(exist = false)
    private String colorLabel;

    public String getColorLabel() {
        if (this.color == null) {
            return "";
        }

        return this.color.getLabel();
    }
}
