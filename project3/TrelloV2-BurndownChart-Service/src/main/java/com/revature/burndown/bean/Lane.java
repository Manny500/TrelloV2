package com.revature.burndown.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="LANE")
@Component
public class Lane {
	
	@Id
	@Column(name="L_ID")
	private int laneId;
	
	@Column(name="B_ID")
	private int bId;
	
	@Column(name="L_TITLE")
	private String laneTitle;
	
	public Lane() {
		
	}

	public Lane(int laneId, int bId, String laneTitle) {
		super();
		this.laneId = laneId;
		this.bId = bId;
		this.laneTitle = laneTitle;
	}

	public int getLaneId() {
		return laneId;
	}

	public void setLaneId(int laneId) {
		this.laneId = laneId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getLaneTitle() {
		return laneTitle;
	}

	public void setLaneTitle(String laneTitle) {
		this.laneTitle = laneTitle;
	}
	
	
	
}
