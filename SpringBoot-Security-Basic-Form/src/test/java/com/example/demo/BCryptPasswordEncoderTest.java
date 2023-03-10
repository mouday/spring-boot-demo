package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {

    @Test
    public void encode(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("wangwu");
        System.out.println(encode);
        // $2a$10$n/9PAb7sazKWPg0S0MUFPeYd5cZ5VSPxXumvqsgq2M97wdu.gcDb.
    }
}
