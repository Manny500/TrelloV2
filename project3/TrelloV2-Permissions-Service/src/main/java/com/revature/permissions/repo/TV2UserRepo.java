package com.revature.permissions.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.permissions.beans.TV2User;

@Repository(value="TV2UserRepoImpl")
public interface TV2UserRepo extends CrudRepository<TV2User,Integer>{

	List<TV2User> findAll();
	
	TV2User save(TV2User user);
}
