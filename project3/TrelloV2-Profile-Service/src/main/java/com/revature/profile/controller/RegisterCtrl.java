package com.revature.profile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.RegisterRepo;

@RestController
public class RegisterCtrl {
	
	@Autowired
	RegisterRepo repo;
	
	TV2User user;
	
	private final static String POST_REGISTER_URL = "/register";
	
	@RequestMapping(POST_REGISTER_URL)
	public ResponseEntity<TV2User>  registerUser(@RequestBody TV2User user, HttpServletRequest request){
		
		System.err.println("Inside registerUser");
		
		TV2User clientUser = new TV2User(user);
		
		repo.save(clientUser);
		
		return ResponseEntity.ok(user);
	}

}
