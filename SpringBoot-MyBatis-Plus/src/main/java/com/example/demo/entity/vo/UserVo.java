package com.example.demo.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 返回前端的数据
 */
@Data
public class UserVo {
    @JsonProperty("user_id")
    private Integer id;

    private String name;

    @JsonProperty("name_label")
    private String nameLabel;

    public String getNameLabel() {
        return "[" + this.getName() + ']';
    }
}
