package com.revature.permissions.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.permissions.beans.TV2User;

@Repository(value="TV2UserRepoImpl")
public interface TV2UserRepo extends CrudRepository<TV2User,Integer>{

	/**
	 * get back a list of all the TV2Users from the database
	 */
	List<TV2User> findAll();
	
	/**
	 * add a user to TV2_User table in the database.
	 */
	@SuppressWarnings("unchecked")
	TV2User save(TV2User user);
}
