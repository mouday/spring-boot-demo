package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 参数校验失败
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        StringBuilder sb = new StringBuilder("校验失败:");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField())
                    .append("：")
                    .append(fieldError.getDefaultMessage())
                    .append(", ");
        }
        
        String msg = sb.toString();
        return JsonResult.error("参数校验失败" + msg);
    }

    /**
     * 参数校验失败
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResult handleConstraintViolationException(ConstraintViolationException ex) {
        return JsonResult.error("参数校验失败" + ex.getMessage());
    }
}