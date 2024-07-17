package com.demo.advice;

import com.demo.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResultVO handle(Exception ex) {
        ex.printStackTrace();
        return ResultVO.error(ex.getMessage());
    }
}
