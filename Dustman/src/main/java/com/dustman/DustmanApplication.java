package com.dustman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DustmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DustmanApplication.class, args);
	}

}
