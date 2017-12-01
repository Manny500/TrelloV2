package com.revature.authservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.authservice.bean.TV2User;

public interface TV2UserDao extends JpaRepository<TV2User, Integer> {

	Optional<TV2User> findByUserName(String userName);

}