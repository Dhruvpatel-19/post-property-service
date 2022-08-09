package com.example.postpropertyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PostPropertyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostPropertyServiceApplication.class, args);
	}

}
