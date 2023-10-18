package com.example.demo;


import com.example.demo.service.ReadingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ReadingConfiguration.class)
class ReadingServiceTests {

	@Autowired
	private ReadingService readingService;

	@Test
	void testReadingService() {
		readingService.reading();
	}
}
