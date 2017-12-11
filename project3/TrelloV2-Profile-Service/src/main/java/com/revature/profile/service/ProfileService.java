package com.revature.profile.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.profile.bean.TV2User;
import com.revature.profile.repo.ProfileRepo;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepo profileRepo;
	
	public List<TV2User> findAll(){
		return profileRepo.findAll();
	}
	
	public TV2User findByUserId(int userId) {
		return profileRepo.findByUserId(userId);
	}
	
	public TV2User save(TV2User user) {
		return profileRepo.save(user);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
     * This setter method should be used only by unit tests.
     * @param personRepository
     */
	public void setProfileRepo (ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
	}
}
