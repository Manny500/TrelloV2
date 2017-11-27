package com.revature.board.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.board.beans.Card;
import com.revature.board.beans.Lane;
import com.revature.board.repo.CardRepo;
import com.revature.board.repo.LaneRepo;

@RestController
public class BoardCtrl {
	private final static String ADD_CARD_URL = "/addCard";
	private final static String ADD_LANE_URL = "/addLane";
	@Autowired
	CardRepo cardRepo;
	@Autowired
	LaneRepo laneRepo;
	
	@RequestMapping(ADD_CARD_URL)
	public ResponseEntity<Card> addCard(@RequestBody Card card, HttpServletRequest request){
		System.out.println("addCard Ctrl");
		System.out.println("card Title: "+ card.getcTitle());
		System.out.println("card id??: " + card.getcId());
		Card newCard = new Card();
		newCard.setlId(card.getlId());
		newCard.setcTitle(card.getcTitle());
		newCard.setcWorth(card.getcWorth());
	    newCard.setcDescription(card.getcDescription());
	    cardRepo.save(newCard);
	    return ResponseEntity.ok(card);
	}
	@RequestMapping(ADD_LANE_URL)
	public ResponseEntity<Lane> addlane(@RequestBody Lane lane, HttpServletRequest request){
		
		Lane newLane = new Lane();
		newLane.setLaneId(lane.getLaneId());
		newLane.setbId(lane.getbId());
		newLane.setLaneTitle(lane.getLaneTitle());
	   
	    laneRepo.save(newLane);
	    return ResponseEntity.ok(lane);
	}
}
