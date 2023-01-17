package com.example.demo;

import com.example.demo.entity.AppData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProfileMethodTest {

	@Autowired
	private AppData appConfigData;

	@Test
	void testAppData() {

		System.out.println(appConfigData);

		// spring.profiles.active=development
		// AppData(environmentName=app development)

		// spring.profiles.active=production
		// AppData(environmentName=app production)
	}
}
