package com.app.my_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.ComponentScan; //สแกนอ่าน คอมโพเนนต์ที่อยู่ใน package com.app.my_project

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.app.my_project")
public class MyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello Spring Boot Framework";
	}
}