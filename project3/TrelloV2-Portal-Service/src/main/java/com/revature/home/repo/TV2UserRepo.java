package com.revature.home.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.revature.home.beans.TV2User;


@Repository(value="TV2UserRepoImpl")
public interface TV2UserRepo extends CrudRepository<TV2User,Integer>{

	TV2User findByUsername(String username);
	
	@SuppressWarnings("unchecked")
	TV2User save(TV2User user);

}
