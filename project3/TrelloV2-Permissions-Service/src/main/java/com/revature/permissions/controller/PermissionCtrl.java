package com.revature.permissions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.permissions.beans.TV2User;
import com.revature.permissions.repo.TV2UserRepo;

@EnableBinding(Sink.class)
@EnableEurekaClient
@RestController
public class PermissionCtrl {
	
	@Autowired
	TV2UserRepo repo;
	
	private final static String GET_USER_URL = "/viewAll";

	@GetMapping(GET_USER_URL)
	public ResponseEntity<List<TV2User>> getAllUsers() {
		
		
		return ResponseEntity.ok(repo.findAll());
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 1")
	public void updateProfile(@RequestBody TV2User user) {
		repo.save(user);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 2")
	public void addUser(@RequestBody TV2User user) {
		repo.save(user);
	}

}
