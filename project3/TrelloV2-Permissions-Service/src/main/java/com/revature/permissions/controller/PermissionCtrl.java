package com.revature.permissions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.revature.permissions.beans.TV2User;
import com.revature.permissions.repo.TV2UserRepo;

@EnableEurekaClient
@Controller
public class PermissionCtrl {
	
	@Autowired
	TV2UserRepo repo;
	
	private final static String GET_USER_URL = "/viewAll";

	@GetMapping(GET_USER_URL)
	public ResponseEntity<List<TV2User>> getAllUsers() {
		
		
		return ResponseEntity.ok(repo.findAll());
	}

}
