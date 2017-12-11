package com.revature.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.home.beans.TV2User;
import com.revature.home.repo.TV2UserRepo;

@Service
public class LoginService {

	@Autowired
	private TV2UserRepo repo;
	
	/**
	 * 
	 * @param userName - string representing username column.
	 * @return - TV2User that has matching username with userName param.
	 */
	public TV2User findByUsername(String userName) {
		return repo.findByUserName(userName);

	}
	
	/**
	 * 
	 * @param user - a TV2User object
	 * @return - TV2User that was saved to the database.
	 */
	public TV2User save(TV2User user) {
		return repo.save(user);
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}