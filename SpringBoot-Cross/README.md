# Spring-Boot-Cross

## 后端

提供接口数据

http://localhost:8080/json

```java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 返回json数据 支持GET POST
     * @return
     */
    @RequestMapping(value="/json", method={RequestMethod.GET, RequestMethod.POST})
    public Object json() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 23);

        return map;
    }

}

```

允许跨域

```java
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrossConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(false);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}

```

## 前端

前端发送跨域请求

可以通过 live-server 启动静态服务

http://127.0.0.1:5500/index.html

```html
<html lang="en">
  <head>
    <title>Demo</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/axios/0.21.1/axios.min.js"></script>
  </head>

  <body>
    <script>
      // get
      axios
        .get('http://localhost:8080/json', {
          params: {
            name: 'Tom',
          },
        })
        .then((res) => {
          console.log(res);
        });

      // post
      axios
        .post('http://localhost:8080/json', {
          name: 'Tom',
        })
        .then((res) => {
          console.log(res);
        });

      // 携带自定义头部
      axios
        .post(
          'http://localhost:8080/json',
          {
            name: 'Tom',
          },
          {
            headers: {
              'X-Token': 'token',
            },
          },
        )
        .then((res) => {
          console.log(res);
        });
    </script>
  </body>
</html>
```

如果不允许跨域，控制台会出现报错

```
Access to XMLHttpRequest at 'http://localhost:8080/'
from origin 'http://127.0.0.1:5500' has been blocked by CORS policy:
No 'Access-Control-Allow-Origin' header is present on the requested resource.
```
