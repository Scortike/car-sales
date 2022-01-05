package com.carsale.serviceassortment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceAssortmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAssortmentApplication.class, args);
	}

}
