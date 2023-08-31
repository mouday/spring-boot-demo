package com.example.demo.controller;

import com.example.demo.entity.Message;
import com.example.demo.service.SseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/sse")
public class IndexController {

    @Autowired
    private SseService sseService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 创建SSE连接
     *
     * @return
     */
    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sse() {
        String uuid = UUID.randomUUID().toString();
        log.info("新用户连接：{}", uuid);
        return sseService.connect(uuid);
    }

    /**
     * 广播消息
     *
     * @param message
     */
    @PostMapping("/sendMessage")
    @ResponseBody
    public void sendMessage(@RequestBody Message message) {
        sseService.sendMessage(message);
    }
}
