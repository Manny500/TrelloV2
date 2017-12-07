package com.revature.profile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
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
@EnableResourceServer
public class ProfileCtrl {
	
	private final static String POST_PROFILE_URL = "/userInfo";
	private final static String POST_UPDATE_URL = "/updateInfo";
	private final static String POST_REGISTER_URL = "/register";

	
	@Autowired
	Messaging mysource;
	
	@Autowired
	ProfileRepo profileRepo;
	
	@GetMapping(value = "/hope")
	  public List<TV2User> testObject(){
		  
	    return service.findAll();
	}

	@RequestMapping(POST_REGISTER_URL)
	public ResponseEntity<TV2User>  registerUser(@RequestBody TV2User user, HttpServletRequest request){
		

		String payload = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			payload = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		mysource.profileChannel().send(MessageBuilder.withPayload(payload).setHeader("macro", 2).build());
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


		String payload = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			payload = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		mysource.profileChannel().send(MessageBuilder.withPayload(payload).setHeader("macro", 1).build());
		profileRepo.save(user);
    
		return ResponseEntity.ok(user);
		
		
	}
}