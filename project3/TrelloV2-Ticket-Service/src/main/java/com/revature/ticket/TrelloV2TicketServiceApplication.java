package com.revature.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TrelloV2TicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloV2TicketServiceApplication.class, args);
	}
}
