package com.revature.profile.repo;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.revature.profile.bean.TV2User;

@Service
public interface ProfileRepo extends CrudRepository<TV2User, Integer> {
	
	//find all users
	List<TV2User> findAll();
	
	//find a user by userid
	TV2User findByUserId(int userId);
    
	//update User
	@SuppressWarnings("unchecked")
	TV2User save(TV2User user);
	
	//for junit Test
	TV2User findOne(int userId);
	
}
 