package com.revature.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(allowedHeaders="*",allowCredentials="true")
@SpringBootApplication
@EnableEurekaClient
public class AuthenticationServer {

   public static void main(String[] args) {
      SpringApplication.run(AuthenticationServer.class, args);
   }

}