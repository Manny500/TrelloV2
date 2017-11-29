package com.revature.home.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.home.beans.TV2User;
import com.revature.home.repo.TV2UserRepo;


public class LoginService {
	@Resource
	@Autowired
	private TV2UserRepo repo;

	public TV2User findByUsername(String username) {
		return repo.findByUsername(username);
	}
	public TV2User save(TV2User user) {
		return repo.save(user);
	}
}
