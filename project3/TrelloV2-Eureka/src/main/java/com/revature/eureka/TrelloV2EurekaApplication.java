package com.revature.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TrelloV2EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloV2EurekaApplication.class, args);
	}
}
