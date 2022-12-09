package com.github.mouday.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * 参考
 * https://www.jianshu.com/p/3998ea8b53a8
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exceptionHandler(Throwable error) {
        return JsonResult.error(error.getMessage(), -1);
    }
}
