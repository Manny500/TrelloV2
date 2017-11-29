package com.revature.home.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.home.beans.TV2User;

@Repository(value="TV2UserRepoImpl")
public interface TV2UserRepo extends CrudRepository<TV2User,Integer>{

	TV2User findByUserName(String userName);

}
