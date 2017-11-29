package com.revature.profile.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.profile.bean.TV2User;
import com.revature.profile.message.Messaging;
import com.revature.profile.repo.ProfileRepo;
import com.revature.profile.service.ProfileService;

@RestController
public class ProfileCtrl {
	
	private final static String POST_PROFILE_URL = "/userInfo";
	private final static String POST_UPDATE_URL = "/updateInfo";
	private final static String POST_REGISTER_URL = "/register";

	
	@Autowired
	Messaging mysource;
	
	@Autowired
	ProfileRepo profileRepo;

	@RequestMapping(POST_REGISTER_URL)
	public ResponseEntity<TV2User>  registerUser(@RequestBody TV2User user, HttpServletRequest request){
		
		System.err.println("about update to macro 2");

		String payload = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			payload = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.err.println(payload);
		mysource.profileChannel().send(MessageBuilder.withPayload(payload).setHeader("macro", 2).build());
		System.err.println("sent update to macro 2");
		profileRepo.save(user);
		
		return ResponseEntity.ok(user);
	}
	
	@Autowired
	ProfileService service;
	
	@RequestMapping(POST_PROFILE_URL)
	public ResponseEntity<TV2User> displayProfile(@RequestBody TV2User user, HttpServletRequest request){
		
		user = service.findByUserId(user.getUserId());
		
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(POST_UPDATE_URL)
	public ResponseEntity<TV2User>  updateProfile(@RequestBody TV2User user, HttpServletRequest request){

		System.err.println("about update to macro 1");

		String payload = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			payload = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.err.println(payload);
		mysource.profileChannel().send(MessageBuilder.withPayload(payload).setHeader("macro", 1).build());
		System.err.println("sent update to macro 1");
		profileRepo.save(user);
    
		return ResponseEntity.ok(user);
		
		
	}
}
