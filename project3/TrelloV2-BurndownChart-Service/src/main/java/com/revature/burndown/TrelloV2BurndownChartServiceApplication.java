package com.revature.burndown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TrelloV2BurndownChartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloV2BurndownChartServiceApplication.class, args);
	}
}