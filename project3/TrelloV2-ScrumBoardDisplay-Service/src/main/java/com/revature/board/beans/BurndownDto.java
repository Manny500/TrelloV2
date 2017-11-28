package com.revature.board.beans;

import java.util.List;

public class BurndownDto {
	private int bId;
	private List<Lane> lanes;
	private List<Card> cards;
	
	public BurndownDto() {
		
	}
	
	
	public BurndownDto(int bId, List<Lane> lanes, List<Card> cards) {
		super();
		this.bId = bId;
		this.lanes = lanes;
		this.cards = cards;
	}


	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public List<Lane> getLanes() {
		return lanes;
	}
	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	
}
