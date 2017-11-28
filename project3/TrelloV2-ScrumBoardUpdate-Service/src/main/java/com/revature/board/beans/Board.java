package com.revature.board.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="BOARD")
@Component
public class Board {
	@Id
	@Column(name="B_ID")
	private int bId;
	
	@Column(name="TV2_ID")
	private int tv2Id;
	
	@Column(name="B_TOTAL")
	private int bTotal;
	
	@Column(name="B_TITLE")
	private String bTitle;
	
	@Column(name="TV2_TEAM")
	private int tv2Team;
	
	public Board() {
		
	}
	
	public Board(int bId, int tv2Id, String bTitle) {
		super();
		this.bId = bId;
		this.tv2Id = tv2Id;
		this.bTitle = bTitle;
		
	}

	public Board(int bId, int tv2Id, int bTotal, String bTitle, int tv2Team) {
		super();
		this.bId = bId;
		this.tv2Id = tv2Id;
		this.bTotal = bTotal;
		this.bTitle = bTitle;
		this.tv2Team = tv2Team;
	}



	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public int getTv2Id() {
		return tv2Id;
	}

	public void setTv2Id(int tv2Id) {
		this.tv2Id = tv2Id;
	}

	public int getbTotal() {
		return bTotal;
	}

	public void setbTotal(int bTotal) {
		this.bTotal = bTotal;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public int getTv2Team() {
		return tv2Team;
	}

	public void setTv2Team(int tv2Team) {
		this.tv2Team = tv2Team;
	}
	
	
}
