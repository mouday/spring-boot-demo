package com.example.demo.utils;

import org.junit.jupiter.api.Test;

public class UrlUtilTest {
    @Test
    public void urlJoin(){
        System.out.println(UrlUtil.urlJoin("/", "/path"));

        System.out.println(UrlUtil.urlJoin("http://127.0.0.1:8080/", "/path/file"));

        System.out.println(UrlUtil.urlJoin("http://127.0.0.1:8080/", "/path/", "/file"));

    }

    @Test
    public void urlTrim(){
        System.out.println(UrlUtil.urlTrim("/path/"));
    }

    @Test
    public void urlTrimLeft(){
        System.out.println(UrlUtil.urlTrimLeft("/path//"));
    }

    @Test
    public void urlTrimRight(){
        System.out.println(UrlUtil.urlTrimRight("//path///"));
    }
}
