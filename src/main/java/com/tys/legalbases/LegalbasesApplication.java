package com.tys.legalbases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; // Import EnableFeignClients

@SpringBootApplication
@EnableFeignClients // Enable Feign clients
public class LegalbasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegalbasesApplication.class, args);
	}

}