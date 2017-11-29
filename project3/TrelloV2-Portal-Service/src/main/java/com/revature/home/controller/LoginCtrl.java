package com.revature.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.home.beans.TV2User;
import com.revature.home.repo.TV2UserRepo;

@EnableBinding(Sink.class)
@Controller
public class LoginCtrl {
	@Autowired
	TV2UserRepo userRepo;
	
	private final static String POST_USER_URL = "/login";

	@RequestMapping(POST_USER_URL)
	public ResponseEntity<TV2User> authenticateUser(@RequestBody TV2User user) {
		System.out.println("helllo from login");
		TV2User tu = userRepo.findByUsername(user.getUsername());
		
		if(tu != null) {
				
			if(user.getUsername().equals(tu.getUsername()) && user.getPassword().equals(tu.getPassword())) {
				
				user = tu;
			}else {
				user = new TV2User();
			}
		}else {
			user = new TV2User();
		}
		System.out.println(user);
		return ResponseEntity.ok(user);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 1")
	public void updateUser(@RequestBody TV2User user) {

		userRepo.save(user);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 2")
	public void addUser(@RequestBody TV2User user) {
		System.out.println("tryinng to sync registered user PORTALDB!!!!@@@@@!@!@!@!@!@!@");
		userRepo.save(user);
	}

}
