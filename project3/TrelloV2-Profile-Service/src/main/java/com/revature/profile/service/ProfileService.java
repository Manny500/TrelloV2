package com.revature.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.ProfileRepo;

@Service
public class ProfileService {
	private ProfileRepo repo;
	
	@Autowired
	public ProfileService(ProfileRepo repo) {
		this.repo = repo;
	}
	
	
	public TV2User findByUserId(int userId) {
		return repo.findByUserId(userId);
	}
	public TV2User save(TV2User user) {
		return repo.save(user);
	}
}
