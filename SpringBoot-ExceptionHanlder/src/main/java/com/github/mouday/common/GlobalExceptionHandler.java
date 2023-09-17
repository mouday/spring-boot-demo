package com.github.mouday.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    /**
     * 捕获业务异常
     * @param error
     * @return
     */
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public JsonResult appExceptionHandler(Throwable error) {
        return JsonResult.error(error.getMessage(), -1);
    }

    /**
     * 处理404异常
     * @param error
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public JsonResult noHandlerFoundExceptionHandler(NoHandlerFoundException error) {
        return JsonResult.error(error.getMessage(), -1);
    }
}
