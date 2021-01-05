package com.example.demofilterinterceptorlistener.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * 监听器
 *
 * 统计当前在线人数 HttpSessionListener不执行
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    private static AtomicInteger userCount = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        userCount.getAndDecrement();
        se.getSession().getServletContext().setAttribute("userCount", userCount.get());
        System.out.println("在线人数减少到: " + userCount.get());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        userCount.getAndIncrement();

        se.getSession().getServletContext().setAttribute("userCount", userCount.get());
        System.out.println("在线人数增加到: " + userCount.get());
    }

}
