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
import com.revature.profile.repo.RegisterRepo;

@RestController
public class RegisterCtrl {
	
	@Autowired
	RegisterRepo repo;
	
	@Autowired
	Messaging mysource;
	
	private final static String POST_REGISTER_URL = "/register";
	
	@RequestMapping(POST_REGISTER_URL)
	public ResponseEntity<TV2User>  registerUser(@RequestBody TV2User user, HttpServletRequest request){
		
		
		String payload = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			payload = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		repo.save(user);
		mysource.profileChannel().send(MessageBuilder.withPayload(payload).setHeader("macro", 2).build());
		
		return ResponseEntity.ok(user);
	}

}
