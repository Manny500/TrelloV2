package com.revature.profile.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.ProfileRepo;

@RestController
public class ProfileCtrl {
	@Autowired
	ProfileRepo profileRepo;
	
	TV2User user;
	
	int aaaaaaaaaaa = 1; //get User ID
	
	private final static String POST_PROFILE_URL = "/userInfo";
	
	@RequestMapping(POST_PROFILE_URL)
	public ResponseEntity<TV2User> displayProfile(){
		System.out.println("Inside displayProfile Controller");
		
		user = (TV2User) profileRepo.findByUserId(aaaaaaaaaaa);
		System.out.println("profile user : " + user);
		
		return ResponseEntity.ok(user);
	}
}