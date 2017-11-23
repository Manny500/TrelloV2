package com.revature.board.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.board.beans.Board;
import com.revature.board.beans.Card;
import com.revature.board.beans.Lane;
import com.revature.board.repo.BoardRepo;
import com.revature.board.repo.CardRepo;
import com.revature.board.repo.LaneRepo;

@RestController
public class BoardCtrl {
	
	private final static String GET_BOARD_URL = "/home";
	private final static String GET_LANE_URL = "/trello";
	private final static String GET_USER_BOARD_URL = "/user-home";
	private final static String GET_CARD_URL = "/showCard";
	
	@Autowired
	BoardRepo boardRepo;
	
	@Autowired
	LaneRepo laneRepo;
	
	@Autowired
	CardRepo cardRepo;
	
	@GetMapping(GET_BOARD_URL)
	public ResponseEntity<List<Board>> getBoards() {
		
		List<Board> board = new ArrayList<Board>();
		
		board = boardRepo.findAll();
		
		return ResponseEntity.ok(board);
	}
	
	@GetMapping(GET_USER_BOARD_URL)
	public ResponseEntity<List<Board>> getUserBoards() {
		
		List<Board> board = new ArrayList<Board>();
		
		board = boardRepo.findAll();
		
		return ResponseEntity.ok(board);
	}
	
	@GetMapping(GET_LANE_URL)
	public ResponseEntity<List<Lane>> getLanes(){
		List<Lane> lane = new ArrayList<Lane>();
		
		lane = laneRepo.findAll();
		
		return ResponseEntity.ok(lane);
	}
	
	@GetMapping(GET_CARD_URL)
	public ResponseEntity<List<Card>> getCards(){
		System.out.println("============== getCards()");
		List<Card> card = new ArrayList<Card>();
		
		card = cardRepo.findAll();
		
		return ResponseEntity.ok(card);
		
	}
	
	
	
}