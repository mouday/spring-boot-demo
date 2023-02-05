package com.example.demo.webscoket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket服务类
 */
@Component
@Slf4j
@ServerEndpoint("/ws/{userId}")
public class WebSocketServer {
    /**
     * 心跳消息
     */
    private final static String PING = "ping";

    private final static String PONG = "pong";

    /**
     * 存放每个客户端对应的 WebSocketServer 对象
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收 userId
     */
    private String userId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;

        // 加入
        webSocketMap.put(userId, this);

        log.info("新用户上线:" + userId + ", 当前在线人数为:" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (!webSocketMap.containsKey(userId)) {
            return;
        }

        webSocketMap.remove(userId);

        log.info("用户下线:" + userId + ", 当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:" + userId + ",报文:" + message);

        if (PING.equals(message)) {
            try {
                this.sendMessage(PONG);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发消息
     */
    public static void sendMessageToAll(String message) throws IOException {
        for (Map.Entry<String, WebSocketServer> entry : webSocketMap.entrySet()) {
            WebSocketServer webSocketServer = entry.getValue();
            webSocketServer.sendMessage(message);
        }
    }

    /**
     * 单发消息
     */
    public static void sendMessageToUser(String toUserId, String message) throws IOException {
        if (webSocketMap.containsKey(toUserId)) {
            webSocketMap.get(toUserId).sendMessage(message);
        } else {
            log.error("请求的 userId:" + toUserId + "不在该服务器上");
        }
    }

    /**
     * 获取在线人数
     */
    public static int getOnlineCount() {
        return webSocketMap.size();
    }

    /**
     * 用户是否在线
     */
    public static Boolean isOnline(String userId) {
        return webSocketMap.containsKey(userId);
    }

    /**
     * 在线用户
     */
    public static Set<String> getOnlineUsers() {
        Set<String> set = new HashSet<>();
        Enumeration<String> enumeration = webSocketMap.keys();

        while (enumeration.hasMoreElements()) {
            set.add(enumeration.nextElement());
        }

        return set;
    }
}