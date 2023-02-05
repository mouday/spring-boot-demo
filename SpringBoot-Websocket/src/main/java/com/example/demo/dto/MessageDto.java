package com.example.demo.dto;

import lombok.Data;

/**
 * 消息体
 */
@Data
public class MessageDto {
    /**
     * 发送者
     */
    private String fromUserId;

    /**
     * 接收者
     */
    private String toUserId;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 文本消息
     */
    private String text;

    /**
     * 图片消息
     */
    private String image;

    /**
     * 链接消息
     */
    private String link;
}
