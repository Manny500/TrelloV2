package com.revature.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.home.beans.TV2User;
import com.revature.home.repo.TV2UserRepo;
import com.revature.home.service.LoginService;

@EnableBinding(Sink.class)
@RestController
public class LoginCtrl {
	@Autowired
	TV2UserRepo userRepo;
	
	@Autowired
	LoginService service;
	
	private final static String POST_USER_URL = "/login";

	@RequestMapping(POST_USER_URL)
	public ResponseEntity<TV2User> authenticateUser(@RequestBody TV2User user) {
    
		System.out.println("hello from login" + user.getUserName() + user.getPassword());
		TV2User tu = userRepo.findByUserName(user.getUserName());
		System.err.println(tu.getLastName());

		if(user.getUserName().equals(tu.getUserName()) && user.getPassword().equals(tu.getPassword())) {
			user = tu;
		}else {
			user = new TV2User();
		}
		System.out.println(user.getFirstName());
		return ResponseEntity.ok(user);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 1")
	public void updateUser(@RequestBody TV2User user) {
    
		System.err.println("in portal " +user.getFirstName());
		userRepo.save(user);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 2")
	public void addUser(@RequestBody TV2User user) {

		System.err.println("tryinng to sync registered user PORTALDB!!!!@@@@@!@!@!@!@!@!@");
		userRepo.save(user);
	}

}
