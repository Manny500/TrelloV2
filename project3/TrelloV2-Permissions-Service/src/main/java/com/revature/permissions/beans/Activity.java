package com.revature.permissions.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "ACTIVITY")
@Component
public class Activity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "A_ID")
	private int aId;
	
	@Column(name = "B_ID")
	private int bId;
	
	@Column(name = "TV2_FN")
	private String firstName;
	
	@Column(name = "A_ACTION")
	private String action;
	
	public Activity() {
		super();
	}

	public Activity(int aId, int bId, String firstName, String action) {
		super();
		this.aId = aId;
		this.bId = bId;
		this.firstName = firstName;
		this.action = action;
	}

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
