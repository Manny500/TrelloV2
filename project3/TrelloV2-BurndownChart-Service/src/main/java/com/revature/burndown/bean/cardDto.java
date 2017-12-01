package com.revature.burndown.bean;

public class cardDto{
	
	private int cId;
	
	private int cVerify;
	
	private int cWorth;
	
	private int lId;
	
	private String cTitle;
	
	private String cDescription;
	
	private int bId;
	
	private int bTotal;

	public cardDto() {
		super();
	}

	public cardDto(int cId, int cVerify, int cWorth, int lId, String cTitle, String cDescription, int bId, int bTotal) {
		super();
		this.cId = cId;
		this.cVerify = cVerify;
		this.cWorth = cWorth;
		this.lId = lId;
		this.cTitle = cTitle;
		this.cDescription = cDescription;
		this.bId = bId;
		this.bTotal = bTotal;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
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

	public int getlId() {
		return lId;
	}

	public void setlId(int lId) {
		this.lId = lId;
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

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public int getbTotal() {
		return bTotal;
	}

	public void setbTotal(int bTotal) {
		this.bTotal = bTotal;
	}

	
	
	
	
}
