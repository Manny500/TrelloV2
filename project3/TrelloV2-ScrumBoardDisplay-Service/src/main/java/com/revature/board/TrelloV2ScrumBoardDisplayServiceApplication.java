package com.revature.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TrelloV2ScrumBoardDisplayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloV2ScrumBoardDisplayServiceApplication.class, args);
	}
}
