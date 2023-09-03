package com.example.demo.enums;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SexEnumTest {
    @Test
    public void toList() {
        for (SexEnum item : SexEnum.values()) {
            System.out.println(
                    String.format("%s-%s-%s", item.name(), item.getValue(), item.getLabel())
            );
        }
    }
}
