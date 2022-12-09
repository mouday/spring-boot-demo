package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    public void testStream() throws IOException {
        URL url = this.getClass().getResource("/name.txt");

        // @since 1.8
        try (Stream<String> lines = Files.lines(Paths.get(url.getPath()))) {
            lines.forEach(System.out::println);
        }

    }
}
