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
	
	private int tv2Id;
	
	private String bTitle;
	
	private int tv2Team;

	public cardDto() {
		super();
	}

	public cardDto(int cId, int cVerify, int cWorth, int lId, String cTitle, String cDescription, int bId, int bTotal,
			int tv2Id, String bTitle, int tv2Team) {
		super();
		this.cId = cId;
		this.cVerify = cVerify;
		this.cWorth = cWorth;
		this.lId = lId;
		this.cTitle = cTitle;
		this.cDescription = cDescription;
		this.bId = bId;
		this.bTotal = bTotal;
		this.tv2Id = tv2Id;
		this.bTitle = bTitle;
		this.tv2Team = tv2Team;
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

	public int getTv2Id() {
		return tv2Id;
	}

	public void setTv2Id(int tv2Id) {
		this.tv2Id = tv2Id;
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
