package com.revature.home.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="TV2_USER")
@Component
public class TV2User {
	@Id
	@Column(name = "TV2_ID")
	private int userId;
	
	@Column(name = "TV2_FN")
	private String firstname;
	
	@Column(name = "TV2_LS")
	private String lastname;
	
	@Column(name = "TV2_USERNAME")
	private String username;
	
	@Column(name = "TV2_PASSWORD")
	private String password;
	
	@Column(name = "TV2_TEAM")
	private int team;
	
	@Column(name = "RT_ID")
	private int roleType;
	
	@Column(name = "TV2_EMAIL")
	private String email;

	public TV2User() {
		
	}
	
	
	public TV2User(int userId, String username, String password, int team, int roleType) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.team = team;
		this.roleType = roleType;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
