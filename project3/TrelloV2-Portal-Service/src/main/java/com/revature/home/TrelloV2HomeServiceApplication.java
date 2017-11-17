package com.revature.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TrelloV2HomeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloV2HomeServiceApplication.class, args);
	}
}
