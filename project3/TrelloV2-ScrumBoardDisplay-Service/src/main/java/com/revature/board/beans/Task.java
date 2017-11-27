package com.revature.board.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="TASK")
@Component
public class Task {
	@Id
	@Column(name="T_ID")
	private int taskId;
	
	@Column(name="C_ID")
	private int cardId;
	
	@Column(name="T_COMPLETE")
	private int status;
	
	@Column(name="T_INFO")
	private String info;
	
	public Task() {
		
	}

	public Task(int taskId, int cardId, int status, String info) {
		super();
		this.taskId = taskId;
		this.cardId = cardId;
		this.status = status;
		this.info = info;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
	

}
