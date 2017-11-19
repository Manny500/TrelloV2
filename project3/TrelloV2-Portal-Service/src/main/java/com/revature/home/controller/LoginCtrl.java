package com.revature.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.home.beans.TV2User;
import com.revature.home.repo.TV2UserRepo;

@EnableEurekaClient
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

}
