package com.example.demo.controller;

import com.example.demo.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler()
    public Map<String, Object> handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, 400);
        map.put("code", -1);
        map.put("msg", e.getMessage());
        return map;
    }
}
