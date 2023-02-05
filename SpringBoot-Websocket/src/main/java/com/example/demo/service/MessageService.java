package com.example.demo.service;

import com.example.demo.dto.MessageDto;

/**
 * 消息推送的服务
 */
public interface MessageService {
    public void sendMessageToUser(MessageDto messageDto);

    public void sendMessageToAllUser(MessageDto messageDto);

}
