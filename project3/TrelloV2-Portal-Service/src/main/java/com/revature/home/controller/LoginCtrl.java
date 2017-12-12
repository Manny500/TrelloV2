package com.revature.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.home.beans.TV2User;
import com.revature.home.repo.TV2UserRepo;
import com.revature.home.service.LoginService;

@EnableBinding(Sink.class)
@RestController
@EnableResourceServer 
public class LoginCtrl {
	
	@Autowired
	TV2UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	LoginService service;

	private final static String POST_USER_URL = "/login";

	/**
	 * 
	 * @param user - a TV2User object
	 * 	properties of this object comes from inputs that the user typed in on client-side Angular
	 * @return - status code 
	 */
	@RequestMapping(POST_USER_URL)
	public ResponseEntity<TV2User> authenticateUser(@RequestBody TV2User user) {
    
		TV2User tu = userRepo.findByUserName(user.getUserName());
		
		if(user.getUserName().equals(tu.getUserName()) && passwordEncoder.matches(user.getPassword(), tu.getPassword())) {
			user = tu;
		}else {
			user = new TV2User();
		}

		user.setPassword("***");
		return ResponseEntity.ok(user);
	}
	
	/**
	 * Waiting/listening for rabbitmq to send a TV2User object (comes from client-side Angular)
	 * @param user - a TV2User object that comes from the stream.
	 * 	 updates user in database with new property values.
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 1")
	public void updateUser(@RequestBody TV2User user) {
    

		if(user.getPassword().equals("***") ) {
			TV2User temp = userRepo.findByUserName(user.getUserName());
			user.setPassword(temp.getPassword());
		}else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		userRepo.save(user);
	}
	
	/**
	 * Waiting/listening for rabbitmq to send a TV2User object (comes from client-side Angular)
	 * @param user - a TV2User object that comes from the stream.
	 * 	 adds user to database.
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['macro'] == 2")
	public void addUser(@RequestBody TV2User user) {


		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}

}
