package com.carsale.servicecomments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceCommentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCommentsApplication.class, args);
	}

}
