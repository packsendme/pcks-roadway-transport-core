package com.packsendme.roadway.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Transport_Application {

	public static void main(String[] args) {
		SpringApplication.run(Transport_Application.class, args);
	}
}

