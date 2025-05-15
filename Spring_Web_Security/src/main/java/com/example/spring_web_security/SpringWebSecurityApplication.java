package com.example.spring_web_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class SpringWebSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebSecurityApplication.class, args);
	}

}
