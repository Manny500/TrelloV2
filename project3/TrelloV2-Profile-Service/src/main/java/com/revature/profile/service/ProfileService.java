package com.revature.profile.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.ProfileRepo;

@Service
public class ProfileService {
	
	//@Resource
	@Autowired
	private ProfileRepo profileRepo;
	
//	@Autowired
//	public ProfileService(ProfileRepo profileRepo) {
//		this.profileRepo = profileRepo;
//	}
	
	public List<TV2User> findAll(){
		return profileRepo.findAll();
	}
	
	public TV2User findByUserId(int userId) {
		return profileRepo.findByUserId(userId);
	}
	public TV2User save(TV2User user) {
		return profileRepo.save(user);
	}
	
	
	/**
     * This setter method should be used only by unit tests.
     * @param personRepository
     */
	public void setProfileRepo (ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
	}
}
