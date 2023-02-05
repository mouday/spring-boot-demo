# SpringBoot WebSocket

文章地址：https://blog.csdn.net/mouday/article/details/128892079

思路：

后端通过websocket向前端推送消息，前端统一使用http协议接口向后端发送数据

websocket 前端测试 ：http://www.easyswoole.com/wstool.html

依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

项目目录

```
$ tree 
.
├── README.md
├── demo.iml
├── pom.xml
└── src
    ├── main
        ├── java
        │   └── com
        │       └── example
        │           └── demo
        │               ├── Application.java
        │               ├── config
        │               │   ├── WebMvcConfig.java
        │               │   └── WebSocketConfig.java
        │               ├── consts
        │               │   └── MessageTypeConst.java
        │               ├── controller
        │               │   ├── IndexController.java
        │               │   └── MessageController.java
        │               ├── dto
        │               │   └── MessageDto.java
        │               ├── service
        │               │   ├── MessageService.java
        │               │   └── impl
        │               │       └── MessageServiceImpl.java
        │               └── webscoket
        │                   └── WebSocketServer.java
        └── resources
            ├── application.properties
            ├── static
            │   ├── js
            │   │   ├── index.js
            │   │   └── utils.js
            │   └── libs
            │       └── axios
            │           └── 1.3.2
            │               └── axios.min.js
            └── templates
                └── websocket.html
```

完整依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.7</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>demo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-websocket</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-freemarker</artifactId>
		</dependency>

		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.68</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```

配置

```java
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 启用WebSocket
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
```

WebSocketServer.java

```java
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
```

前端页面 websocket.html

```html
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Java后端WebSocket实现</title>
</head>

<body>
    <h2>Welcome WebSocket</h2>

    <div>
        <input id="textInput" type="text" placeholder="message"/>

        <button id="sendMessageButton">发送消息</button>
        <button id="closeConnectButton">关闭连接</button>
        <button id="sendPingButton">发送ping</button>
    </div>

    <hr/>

    <div id="message"></div>

    <script type="text/javascript" src="/static/libs/axios/1.3.2/axios.min.js"></script>
    <script type="text/javascript" src="/static/js/utils.js"></script>
    <script type="text/javascript" src="/static/js/index.js"></script>
</body>

</html>
```

前端逻辑 index.js

```js
/**
 * 程序入口
 */

// 心跳消息
var PING = "ping";

var PONG = "pong";

// 获取一个用户id
var uuid = utils.getUUID();
var url = "ws://127.0.0.1:8080/ws/" + uuid;

//判断当前浏览器是否支持WebSocket
var websocket = null;

function initWebsocket() {
  if ("WebSocket" in window) {
    websocket = new WebSocket(url);
  } else {
    alert("当前浏览器 Not support websocket");
  }

  //连接成功建立的回调方法
  websocket.onopen = function () {
    setMessageInnerHTML("WebSocket连接成功");
  };

  //接收到消息的回调方法
  websocket.onmessage = function (event) {
    var data = event.data;

    // 忽略心跳消息
    if (data === PONG) {
      return;
    }

    setMessageInnerHTML(JSON.parse(event.data).text);
  };

  //连接关闭的回调方法
  websocket.onclose = function () {
    setMessageInnerHTML("WebSocket连接关闭");
  };

  //连接发生错误的回调方法
  websocket.onerror = function (e) {
    console.log(e);
    setMessageInnerHTML("WebSocket连接发生错误");
  };
}

//关闭WebSocket连接
function closeWebSocket() {
  websocket.close();
}

// 监听窗口关闭事件，当窗口关闭时
// 主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
  closeWebSocket();
};

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
  document.getElementById("message").innerHTML += innerHTML + "<br/>";
}

//发送消息
function sendMessage() {
  var text = textInput.value;
  if (!text) {
    return;
  }

  // 统一发送json格式，便于扩展
  var data = {
    type: "text",
    text: text,
  };

  // websocket.send(JSON.stringify(data));
  axios.post("/api/sendMessageToAllUser", data);

  // setMessageInnerHTML(data.text);
  textInput.value = "";
}

// 事件监听
function bindEventListener() {
  var textInput = document.querySelector("#textInput");
  var sendMessageButton = document.querySelector("#sendMessageButton");
  var closeConnectButton = document.querySelector("#closeConnectButton");
  var sendPingButton = document.querySelector("#sendPingButton");

  textInput.addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
      sendMessage();
    }
  });

  sendMessageButton.addEventListener("click", function (e) {
    sendMessage();
  });

  sendPingButton.addEventListener("click", function (e) {
    websocket.send(PING);
  });

  closeConnectButton.addEventListener("click", function (e) {
    closeWebSocket();
  });
}

/**
 * 入口
 */
(function () {
  initWebsocket();
  bindEventListener();
})();

```
>参考
- [SpringBoot集成WebSocket，实现后台向前端推送信息](https://mp.weixin.qq.com/s/zzoAfT0qCi5yQCoC8ymW_w)
- [Spring Boot 集成 WebSocket 实现服务端推送消息到客户端](https://mp.weixin.qq.com/s/w7zriKMr54KWsxTzOBAg4Q)
 