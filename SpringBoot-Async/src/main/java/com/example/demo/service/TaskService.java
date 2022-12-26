package com.example.demo.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
public class TaskService {
    /**
     * 同步任务
     * @throws InterruptedException
     */
    public void syncTask() throws InterruptedException {
        Thread.sleep(1000 * 2);
        System.out.println("syncTask");
    }

    /**
     * 异步任务
     * @throws InterruptedException
     */
    @Async
    public void asyncTask() throws InterruptedException {
        Thread.sleep(1000 * 2);
        System.out.println("asyncTask");
    }

    /**
     * 异步任务, 有返回值
     * @throws InterruptedException
     */
    @Async
    public Future<String> asyncTaskFuture() throws InterruptedException {
        Thread.sleep(1000 * 2);
        System.out.println("asyncTaskFuture");

        return new AsyncResult<>("asyncTaskFuture");
    }

    /**
     * 自定义线程池 执行异步任务
     * @throws InterruptedException
     */
    @Async("newAsyncExecutor")
    public void asyncTaskNewAsync() throws InterruptedException {
        Thread.sleep(1000 * 2);

        System.out.println("asyncTaskNewAsync");

    }
}
