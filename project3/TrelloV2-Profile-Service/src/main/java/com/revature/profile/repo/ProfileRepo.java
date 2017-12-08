package com.revature.profile.repo;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.revature.profile.bean.TV2User;

@Service
public interface ProfileRepo extends CrudRepository<TV2User, Integer> {
	
	List<TV2User> findAll();
	
	TV2User findByUserId(int userId);
    
	@SuppressWarnings("unchecked")
	TV2User save(TV2User user);
	
	TV2User findOne(int userId);
	
}
 