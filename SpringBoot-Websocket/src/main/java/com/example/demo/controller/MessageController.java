package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息推送接口
 */
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 单人消息推送
     */
    @PostMapping("/api/sendMessageToUser")
    public MessageDto sendMessageToUser(MessageDto messageDto) {

        messageService.sendMessageToUser(messageDto);

        return messageDto;
    }

    /**
     * 全员消息推送
     */
    @PostMapping("/api/sendMessageToAllUser")
    public MessageDto sendMessageToAllUser(@RequestBody MessageDto messageDto) {

        messageService.sendMessageToAllUser(messageDto);

        return messageDto;
    }
}
