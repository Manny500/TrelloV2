package com.revature.permissions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TrelloV2PermissionsService1Application {

	public static void main(String[] args) {
		SpringApplication.run(TrelloV2PermissionsService1Application.class, args);
	}
}