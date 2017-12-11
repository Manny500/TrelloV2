package com.revature.home.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.home.beans.TV2User;


@Repository(value="TV2UserRepoImpl")
public interface TV2UserRepo extends CrudRepository<TV2User,Integer>{

	/**
	 * find a TV2User by its username column.
	 * @param userName
	 * @return -a TV2User that has matching username as the userName param.
	 */
	TV2User findByUserName(String userName);

}
