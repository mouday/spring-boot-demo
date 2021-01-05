# demo-jwt

依赖
```xml
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.4.0</version>
</dependency>
```

注解
```java
package com.example.demojwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 跳过验证
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
}
```

JWT工具类
```java
package com.example.demojwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

/**
 * jwt的工具类
 */
public class JwtUtil {
    // 过期时间为3分钟
    private static final long EXPIRE_TIME = 3 * 60 * 1000;

    // 秘钥
    private static final String TOKEN_SECRET = "jxxxooo9999";

    /**
     * 创建token
     *
     * @param userId
     * @param username
     * @return
     */
    public static String sign(long userId, String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        // 验证 token
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }

        return true;

    }
}

```

拦截器
 
```java
package com.example.demojwt.intercepter;

import com.auth0.jwt.JWT;
import com.example.demojwt.annotation.PassToken;
import com.example.demojwt.entity.User;
import com.example.demojwt.service.UserService;
import com.example.demojwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * 拦截器去获取token并验证token
 * 默认所有接口都需要认证
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");

        // 1、如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 2、检查是否有 PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            return true;
        }

        // 3、检查 token
        if (token == null || "".equals(token)) {
            throw new RuntimeException("token无效");
        }

        // 4、验证 token
        if (!JwtUtil.verify(token)) {
            throw new RuntimeException("token验证失败");
        }

        // 5、验证用户是否存在
        // 获取 token 中的 userId
        Integer userId = JWT.decode(token).getClaim("userId").asInt();

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        return true;

    }
}

```
>参考文章
>1. [SpringBoot集成JWT实现token验证](https://www.jianshu.com/p/e88d3f8151db)
>2. [SpringBoot2.0 - 集成JWT实现token验证](https://blog.csdn.net/milogenius/article/details/96425694)
