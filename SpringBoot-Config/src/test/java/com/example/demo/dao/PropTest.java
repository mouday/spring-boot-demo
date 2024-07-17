package com.example.demo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropTest {
    @Value("${person.name}")
    private BookDao name;

    @Test
    public void testProp() {
        System.out.println(name);
    }
}
