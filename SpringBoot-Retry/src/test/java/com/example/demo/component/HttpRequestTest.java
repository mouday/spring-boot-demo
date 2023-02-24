package com.example.demo.component;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class HttpRequestTest {

    @Autowired
    private HttpRequest httpRequest;

    @Test
    public void getResponse(){
        httpRequest.getResponse();
    }
}
