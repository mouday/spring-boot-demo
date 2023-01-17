package com.example.demo;

import com.example.demo.entity.AppData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProfileClassTest {

	@Autowired
	private AppData appData;

	@Test
	void testAppData() {

		System.out.println(appData);

		// spring.profiles.active=development
		// AppData(environmentName=development)

		// spring.profiles.active=production
		// AppData(environmentName=production)
	}
}
