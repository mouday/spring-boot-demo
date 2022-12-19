# SpringBoot 验证码

原理：

服务端生成验证码和验证码图片，验证码保存到服务端，验证码图片通过二进制流返回给客户端显示，客户提交验证结果到服务端比对

依赖
```xml
<dependency>
	<groupId>cn.hutool</groupId>
    <artifactId>hutool-captcha</artifactId>
    <version>5.8.5</version>
</dependency>
```

控制器
```java
package com.example.demo.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/code")
    public void getCode(HttpSession session, HttpServletResponse response) throws IOException {
        // 利用 hutool 工具，生成验证码图片资源
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 5);

        // 获得生成的验证码字符
        String code = captcha.getCode();

        // 利用 session 来存储验证码
        session.setAttribute("code",code);

        // 将验证码图片的二进制数据写入【响应体 response 】
        captcha.write(response.getOutputStream());
    }


}
```

页面
```html
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>验证码页面</title>
</head>
<body>

<form action="#" method="post">

    <!-- img标签负责向服务器 Controller 请求图片资源 -->
    <img src="/code" id="code">

</form>

</body>

<!-- “点击验证码图片，自动刷新” 脚本 -->
<script>
    var code = document.getElementById("code")

    function handleRefresh() {
        code.src = "/code?time=" + new Date().getTime();
    }

    code.addEventListener("click", handleRefresh)
</script>
</html>
```

> 参考
> [SpringBoot简单几步实现验证码功能](https://mp.weixin.qq.com/s/4IVh9ICXqQrsZo6UadFp4w)