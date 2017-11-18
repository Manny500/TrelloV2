package com.revature.profile.repo;


import org.springframework.data.repository.CrudRepository;

import com.revature.profile.bean.TV2User;

public interface ProfileRepo extends CrudRepository<TV2User, Integer> {
	
	TV2User findByUserId(int userId);
}
 