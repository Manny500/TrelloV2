package com.revature.permissions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.permissions.beans.TV2User;
import com.revature.permissions.repo.TV2UserRepo;

@Service
public class PermissionService {

	@Autowired
	private TV2UserRepo repo;
	
	public List<TV2User> findAll(){
		return repo.findAll();
	}
	
	public TV2User save(TV2User user){
		return repo.save(user);
	}
}
