package com.github.mouday.controller;


import com.github.mouday.common.JsonResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("${server.error.path:/error}")
public class GlobalErrorController implements ErrorController {

    /**
     * html 错误
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public void errorHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("errorHtml");
    }

    /**
     * json 错误
     *
     * @param request
     * @return
     */
    @RequestMapping
    @ResponseBody
    public JsonResult error(HttpServletRequest request, HttpServletResponse response) {

        Throwable error = (Throwable) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);

        String message;
        if (error == null) {
            message = "未知错误";
        } else {
            message = error.getMessage();
            message = message.substring(message.lastIndexOf(":") + 1).trim();
        }

        return JsonResult.error(message);
    }
}
