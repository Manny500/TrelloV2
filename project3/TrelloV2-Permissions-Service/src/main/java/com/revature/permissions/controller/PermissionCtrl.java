package com.revature.permissions.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final static String GET_USER_URL = "/viewAll";
	private final static String ACTIVITY_URL = "/sendActivity";
	private final static String GET_ACTIVITY_URL = "/getActivity";

	
	/**
	 * get all users from database when user hits /viewAll on frontend
	 * @return - status code
	 */
	@GetMapping(GET_USER_URL)
	public ResponseEntity<List<TV2User>> getAllUsers() {
		
		List<TV2User> user = service.findAll();
		for(int i = 0; i < user.size(); i++) {
			user.get(i).setPassword("***");
		}
		return ResponseEntity.ok(user);
	}
	
	/**
	 * update the TV2User in database
	 * @param user - TV2User object 
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 1")
	public void updateProfile(@RequestBody TV2User user) {

		if((user.getPassword().equals("***")) ) {
			TV2User temp = service.findById(user.getUserId());
			user.setPassword(temp.getPassword());
		}else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		service.save(user);


	}
	
	/**
	 * add user into database
	 * @param user - a TV2User object
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 2")
	public void addUser(@RequestBody TV2User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		service.save(user);
	}
	
	/**
	 * save the activity of users making any changes to lanes, cards, and tasks. 
	 * 	e.g. user deleted card 
	 * @param activity - activity object 
	 * @param request - HttpServletRequest from client-side
	 * @return
	 */
	@RequestMapping(ACTIVITY_URL)
	public Activity saveActivity(@RequestBody Activity activity, HttpServletRequest request) {

		service.save(activity);
		
		return activity;
	}
	
	/**
	 * get a list of all the activity records from database
	 * @return - a list of activity objects
	 */
	@GetMapping(GET_ACTIVITY_URL)
	public ResponseEntity<List<Activity>> getActivity(){
		List<Activity> act = new ArrayList<Activity>();
		act = service.findAllActivity();
		return ResponseEntity.ok(act); 
	}

}
