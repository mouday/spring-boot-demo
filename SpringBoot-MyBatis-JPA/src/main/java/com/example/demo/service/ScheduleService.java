package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    /**
     * second, minute, hour, day of month, month, day of week.
     */
    @Scheduled(cron = "0 * * * * MON-FRI")
    public void task(){
        System.out.println("run task");
    }
}
