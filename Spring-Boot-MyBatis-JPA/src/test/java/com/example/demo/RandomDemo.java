package com.example.demo;

import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        Random random = new Random();

        // [0, bound)
        System.out.println(random.nextInt(10));
        // 8

        // [0, 1)
        System.out.println(Math.random());
        // 0.4743026139690609
    }
}
