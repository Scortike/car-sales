package com.carsale.servicefeedbacks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceFeedbacksApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceFeedbacksApplication.class, args);
	}

}
