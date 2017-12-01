package com.revature.board.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARD")
public class Card implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_ID")
	private int cId;
	
	@Column(name = "L_Id")
	private int lId;
	
	@Column(name = "C_VERIFY")
	private int cVerify;
	
	@Column(name = "C_WORTH")
	private int cWorth;
	
	@Column(name = "C_TITLE")
	private String cTitle;
	
	@Column(name = "C_DESCRIPTION")
	private String cDescription;

	public Card() {
		super();
	}

	public Card(int lId, int cVerify, int cWorth, String cTitle, String cDescription) {
		super();
		this.lId = lId;
		this.cVerify = cVerify;
		this.cWorth = cWorth;
		this.cTitle = cTitle;
		this.cDescription = cDescription;
	}


	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getlId() {
		return lId;
	}

	public void setlId(int lId) {
		this.lId = lId;
	}

	public int getcVerify() {
		return cVerify;
	}

	public void setcVerify(int cVerify) {
		this.cVerify = cVerify;
	}

	public int getcWorth() {
		return cWorth;
	}

	public void setcWorth(int cWorth) {
		this.cWorth = cWorth;
	}

	public String getcTitle() {
		return cTitle;
	}

	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}

	public String getcDescription() {
		return cDescription;
	}

	public void setcDescription(String cDescription) {
		this.cDescription = cDescription;
	}

	
}
