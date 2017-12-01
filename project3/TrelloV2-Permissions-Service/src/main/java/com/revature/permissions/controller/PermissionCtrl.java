package com.revature.permissions.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.permissions.beans.Activity;
import com.revature.permissions.beans.TV2User;
import com.revature.permissions.service.PermissionService;

@EnableBinding(Sink.class)
@RestController
@EnableResourceServer
public class PermissionCtrl {
	
	@Autowired
	PermissionService service;
	
	private final static String GET_USER_URL = "/viewAll";
	private final static String ACTIVITY_URL = "/sendActivity";
	private final static String GET_ACTIVITY_URL = "/getActivity";

	@GetMapping(GET_USER_URL)
	public ResponseEntity<List<TV2User>> getAllUsers() {
		
		
		return ResponseEntity.ok(service.findAll());
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 1")
	public void updateProfile(@RequestBody TV2User user) {

		service.save(user);
		System.err.println("updating user");

	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 2")
	public void addUser(@RequestBody TV2User user) {

		System.out.println("tryinng to sync registered user PermissionsDB!!!!@@@@@!@!@!@!@!@!@");
		service.save(user);
	}
	
	@RequestMapping(ACTIVITY_URL)
	public Activity saveActivity(@RequestBody Activity activity, HttpServletRequest request) {
		System.out.println(activity.getAction());
		service.save(activity);
		
		return activity;
	}
	@GetMapping(GET_ACTIVITY_URL)
	public ResponseEntity<List<Activity>> getActivity(){
		List<Activity> act = new ArrayList<Activity>();
		act = service.findAllActivity();
		return ResponseEntity.ok(act); 
	}

}
