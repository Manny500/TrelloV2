package com.revature.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.home.beans.TV2User;
import com.revature.home.repo.TV2UserRepo;

@Service
public class LoginService {

	@Autowired
	private TV2UserRepo repo;

	public TV2User findByUsername(String userName) {
		return repo.findByUserName(userName);

	}
	public TV2User save(TV2User user) {
		return repo.save(user);
	}
}