package com.revature.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.ProfileRepo;

@Service
public class ProfileService {
	
	//@Autowired
	private ProfileRepo profileRepo;
	
	@Autowired
	public ProfileService(ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
	}
	
	
	public TV2User findByUserId(int userId) {
		return profileRepo.findByUserId(userId);
	}
	public TV2User save(TV2User user) {
		return profileRepo.save(user);
	}
}
