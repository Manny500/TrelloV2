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
    
		TV2User tu = userRepo.findByUserName(user.getUserName());

		if(user.getUserName().equals(tu.getUserName()) && user.getPassword().equals(tu.getPassword())) {
			user = tu;
		}else {
			user = new TV2User();
		}
		return ResponseEntity.ok(user);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 1")
	public void updateUser(@RequestBody TV2User user) {
    
		userRepo.save(user);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 2")
	public void addUser(@RequestBody TV2User user) {

		userRepo.save(user);
	}

}
