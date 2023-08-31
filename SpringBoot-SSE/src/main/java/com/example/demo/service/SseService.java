package com.example.demo.service;

import com.example.demo.entity.Message;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {
    SseEmitter connect(String uuid);

    void sendMessage(Message message);
}
