package com.example.demo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeDemo {
    public static void main(String[] args) {
        // 旧API
        System.out.println(System.currentTimeMillis());
        // 1594801771810

        Date date = new Date();
        System.out.println(date.getTime());
        // 1594802296250

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTimeInMillis());
        // 1594802296258

        // 新API
        Instant instant = Instant.now();
        System.out.println(instant.getEpochSecond());
        // 1594802296
        System.out.println(instant.toEpochMilli());
        // 1594802296321

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toEpochSecond(ZoneOffset.of("+8")));
        // 1594802868

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime.toEpochSecond());
        // 1594803196

        // 格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(localDateTime));
        // 2020-07-15 16:44:26
        System.out.println(LocalDateTime.parse("2020-07-15 16:43:47", formatter));
        // 2020-07-15T16:43:47


    }
}
