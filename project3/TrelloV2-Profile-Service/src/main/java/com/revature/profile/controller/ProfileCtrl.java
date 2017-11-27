package com.revature.profile.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.ProfileRepo;

@RestController
public class ProfileCtrl {
	@Autowired
	ProfileRepo profileRepo;
	
	
	int aaaaaaaaaaa = 1; //get User ID
	
	private final static String POST_PROFILE_URL = "/userInfo";
	private final static String POST_UPDATE_URL = "/updateInfo";
	
	@RequestMapping(POST_PROFILE_URL)
	public ResponseEntity<TV2User> displayProfile(@RequestBody TV2User user, HttpServletRequest request){
		
		user = profileRepo.findByUserId(user.getUserId());
		
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(POST_UPDATE_URL)
	public ResponseEntity<TV2User>  updateProfile(@RequestBody TV2User user, HttpServletRequest request){

		
		TV2User clientUser = new TV2User();
		clientUser.setUserId(user.getUserId());
		clientUser.setRoleType(user.getRoleType());
		clientUser.setTeamId(user.getTeamId());
		
		
		profileRepo.save(user);
		return ResponseEntity.ok(user);
	}
}
