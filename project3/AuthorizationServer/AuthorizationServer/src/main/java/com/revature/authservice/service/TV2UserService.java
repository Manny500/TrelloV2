package com.revature.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.authservice.bean.TV2User;
import com.revature.authservice.bean.TV2UserDetails;
import com.revature.authservice.repo.TV2UserDao;

@Service
public class TV2UserService implements UserDetailsService {

	@Autowired
	private TV2UserDao dao;

	@Override
	public TV2UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<TV2User> optUser = dao.findByUserName(username);
		optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optUser.map(TV2UserDetails::new).get();
	}

}
