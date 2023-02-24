package com.example.demo.component;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class HttpRequest {
    private int count = 0;

    /**
     * 模拟网络请求异常
     * @return
     */
    @Retryable(recover = "errorHandler")
    public String getResponse() {
        count++;
        System.out.println("time: " + count);
        if (count < 4) {
            throw new RuntimeException("count: " + count);
        }

        return "success";
    }

    /**
     * 错误处理函数
     * 注意：需要返回 String，否则会抛出方法找不到异常
     * org.springframework.retry.ExhaustedRetryException: Cannot locate recovery method
     *
     * @param e
     * @return
     */
    @Recover
    public String errorHandler(RuntimeException e) {
        System.out.println("errorHandler");
        return "ok";
    }
}
