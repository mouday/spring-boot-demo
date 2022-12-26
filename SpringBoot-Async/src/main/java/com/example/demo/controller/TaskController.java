package com.example.demo.controller;

import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/asyncTask")
    public String asyncTask() throws InterruptedException {
        long start = System.currentTimeMillis();

        taskService.asyncTask();

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return "success";

    }

    @GetMapping("/syncTask")
    public String syncTask() throws InterruptedException {
        long start = System.currentTimeMillis();

        taskService.syncTask();

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return "success";
    }

    @GetMapping("/asyncTaskFuture")
    public String asyncTaskFuture() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();

        Future<String> future = taskService.asyncTaskFuture();

        // 线程等待结果返回
        String result = future.get();

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return result;
    }

    @GetMapping("/asyncTaskNewAsync")
    public String asyncTaskNewAsync() throws InterruptedException {
        long start = System.currentTimeMillis();

        taskService.asyncTaskNewAsync();

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return "success";
    }

    @GetMapping("/completableFuture")
    public String completableFuture(){
        long start = System.currentTimeMillis();

        // CompletableFuture实现异步任务
        CompletableFuture<Void> result = CompletableFuture.runAsync(() -> {

            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("CompletableFuture");
        });

        long end = System.currentTimeMillis();
        log.info("time: {}", end - start);

        return "success";
    }




}
