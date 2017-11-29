package com.revature.home.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "TV2_USER")
@Component
public class TV2User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TV2_ID")
	private int userId;

	@Column(name = "TV2_FN")
	private String firstName;

	@Column(name = "TV2_LS")
	private String lastName;

	@Column(name = "TV2_USERNAME")
	private String userName;

	@Column(name = "TV2_PASSWORD")
	private String password;

	@Column(name = "RT_ID")
	private int roleType;

	@Column(name = "TV2_EMAIL")
	private String email;

	@Column(name = "TV2_TEAM")
	private int teamId;

//	@JsonIgnore
//	@OneToMany(mappedBy = "boardUser", fetch = FetchType.EAGER)
//	private Set<Board> boards = new HashSet<Board>();

	public TV2User() {

	}

	public TV2User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	


	public TV2User(int userId, String firstName, String lastName, String userName, String password, int roleType,
			String email, int teamId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.roleType = roleType;
		this.email = email;
		this.teamId = teamId;
	}
	
	public TV2User(TV2User user) {

		this.userId = user.userId;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.userName = user.userName;
		this.password = user.password;
		this.roleType = user.roleType;
		this.email = user.email;
		this.teamId = user.teamId;
		
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}