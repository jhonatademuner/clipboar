package com.clipboar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ClipboarApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClipboarApiApplication.class, args);
	}
}
