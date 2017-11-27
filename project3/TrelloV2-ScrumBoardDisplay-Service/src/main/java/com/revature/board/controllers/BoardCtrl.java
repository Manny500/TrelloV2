package com.revature.board.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.board.beans.Board;
import com.revature.board.beans.Card;
import com.revature.board.beans.Lane;
import com.revature.board.beans.Task;
import com.revature.board.repo.BoardRepo;
import com.revature.board.repo.CardRepo;
import com.revature.board.repo.LaneRepo;
import com.revature.board.repo.TaskRepo;

@RestController
public class BoardCtrl {
	
	private final static String GET_BOARD_URL = "/home";
	private final static String SWITCH_LANE_URL = "/updateCard";
	private final static String GET_LANE_URL = "/trello";
	private final static String GET_USER_BOARD_URL = "/user-home";
	private final static String GET_CARD_URL = "/showCard";
	private final static String ADD_CARD_URL = "/addCard";
	private final static String GET_TASK_URL = "/showTask";
	private final static String ADD_LANE_URL = "/addLane";
	
	private final static String POST_UPDATE_BOARD_URL = "/updateBoard2";
	
	@Autowired
	BoardRepo boardRepo;
	
	@Autowired
	LaneRepo laneRepo;
	
	@Autowired
	CardRepo cardRepo;
	
	@RequestMapping(POST_UPDATE_BOARD_URL)
	public ResponseEntity<Board> updateBoard(@RequestBody Board board, HttpServletRequest request) {
		System.err.println("In Update");
		
		board = boardRepo.save(board);
		
		
		return ResponseEntity.ok(board);
	}
	@Autowired
	TaskRepo taskRepo;
	
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
	
	@GetMapping(GET_TASK_URL)
	public ResponseEntity<List<Task>> getTasks(){
		System.out.println("============== getTasks() ===============");
		List<Task> task = new ArrayList<Task>();
		
		task = taskRepo.findAll();
		System.out.println(task);
		
		return ResponseEntity.ok(task);
		
	}
	
	@RequestMapping(ADD_CARD_URL)
	public ResponseEntity<Card> addCard(@RequestBody Card card, HttpServletRequest request){
		
		Card newCard = new Card();
		newCard.setlId(card.getlId());
		newCard.setcTitle(card.getcTitle());
		newCard.setcWorth(card.getcWorth());
	    newCard.setcDescription(card.getcDescription());
	    cardRepo.save(newCard);
	    return ResponseEntity.ok(card);
	}
	
	@RequestMapping(SWITCH_LANE_URL)
	public ResponseEntity<Card> switchLane(@RequestBody Card card, HttpServletRequest request){
		
		cardRepo.save(card);
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