package com.github.mouday.controller;

import com.github.mouday.common.JsonResult;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 处理404异常
 * <p>
 * 参考
 * https://www.cnblogs.com/54chensongxia/archive/2020/11/20/14007696.html
 * https://blog.csdn.net/qq_38571892/article/details/123395165
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController extends BasicErrorController {

    public CustomErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    /**
     * 覆盖默认的JSON响应
     */
    @Override
    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);

        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(status);
        }

        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));

        String message = (String) body.get("error");

        Map<String, Object> result = JsonResult.error(message).toMap();

        return new ResponseEntity<>(result, status);
    }
}
