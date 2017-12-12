package com.revature.permissions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.permissions.beans.Activity;
import com.revature.permissions.beans.TV2User;
import com.revature.permissions.repo.ActivityRepo;
import com.revature.permissions.repo.TV2UserRepo;

@Service
public class PermissionService {

	@Autowired
	private TV2UserRepo repo;
	@Autowired
	private ActivityRepo aRepo;
	
	/**
	 * 
	 * @return list of all TV2Users from the database
	 */
	public List<TV2User> findAll(){
		return repo.findAll();
	}
	
	public TV2User findById(int id) {
		return repo.findOne(id);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * add user to the TV2_USER table in the database
	 * @param user
	 * @return
	 */
	public TV2User save(TV2User user){
		return repo.save(user);
	}
	
	/**
	 * add activity to the ACTIVITY table in the database
	 * @param activity
	 * @return
	 */
	public Activity save(Activity activity) {
		return aRepo.save(activity);
	}
	
	/**
	 * get list of all activities from the ACTIVITY table from the database.
	 * @return
	 */
	public List<Activity> findAllActivity(){
		return aRepo.findAllByOrderByAIdAsc();
	}
	
	
}
