package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.consts.MessageTypeConst;
import com.example.demo.dto.MessageDto;
import com.example.demo.service.MessageService;
import com.example.demo.webscoket.WebSocketServer;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public void sendMessageToUser(MessageDto messageDto) {
        if (ObjectUtils.isEmpty(messageDto.getToUserId())) {
            return;
        }

        if (ObjectUtils.isEmpty(messageDto.getFromUserId())) {
            return;
        }

        // 设置默认消息类型
        if (ObjectUtils.isEmpty(messageDto.getType())) {
            messageDto.setType(MessageTypeConst.Text);
        }

        try {
            WebSocketServer.sendMessageToUser(messageDto.getToUserId(), JSON.toJSONString(messageDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessageToAllUser(MessageDto messageDto) {
        // 设置默认消息类型
        if (ObjectUtils.isEmpty(messageDto.getType())) {
            messageDto.setType(MessageTypeConst.Text);
        }

        try {
            WebSocketServer.sendMessageToAll(JSON.toJSONString(messageDto));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
