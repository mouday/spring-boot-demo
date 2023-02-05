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
