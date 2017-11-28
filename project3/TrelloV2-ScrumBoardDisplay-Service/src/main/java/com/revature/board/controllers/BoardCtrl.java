package com.revature.board.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.board.beans.Board;
import com.revature.board.beans.Card;
import com.revature.board.beans.Lane;
import com.revature.board.beans.Task;
import com.revature.board.repo.BoardRepo;
import com.revature.board.repo.CardRepo;
import com.revature.board.repo.LaneRepo;
import com.revature.board.repo.TaskRepo;

@EnableBinding(Sink.class)
@RestController
public class BoardCtrl {

	private final static String GET_BOARD_URL = "/home";
	private final static String GET_LANE_URL = "/trello";
	private final static String GET_USER_BOARD_URL = "/user-home";
	private final static String GET_CARD_URL = "/showCard";
	private final static String GET_TASK_URL = "/showTask";
<<<<<<< HEAD
	private final static String ADD_TASK_URL = "/addTask";

	private final static String ADD_LANE_URL = "/addLane";
	
	private final static String POST_UPDATE_BOARD_URL = "/updateBoard2";
	
=======

>>>>>>> d2cd4c8d88382030359b1190fa4b5709734a8db9
	@Autowired
	BoardRepo boardRepo;

	@Autowired
	LaneRepo laneRepo;

	@Autowired
	CardRepo cardRepo;

	@Autowired
	TaskRepo taskRepo;

	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 1")
	public void addBoard(@RequestBody Board board) {

		boardRepo.save(board);
	}

	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 2")
	public void addLane(@RequestBody Lane lane) {

		laneRepo.save(lane);
	}

	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 3")
	public void addCard(@RequestBody Card card) {

		cardRepo.save(card);
	}

	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 4")
	public void switchLane(@RequestBody Card card) {

		cardRepo.save(card);
	}

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
	public ResponseEntity<List<Lane>> getLanes() {
		
		List<Lane> lane = new ArrayList<Lane>();

		lane = laneRepo.findAll();

		return ResponseEntity.ok(lane);
	}

	@GetMapping(GET_CARD_URL)
	public ResponseEntity<List<Card>> getCards() {
		
		List<Card> card = new ArrayList<Card>();

		card = cardRepo.findAll();

		return ResponseEntity.ok(card);
	}

	@GetMapping(GET_TASK_URL)
	public ResponseEntity<List<Task>> getTasks() {
		
		List<Task> task = new ArrayList<Task>();

		task = taskRepo.findAll();

		return ResponseEntity.ok(task);
	}
<<<<<<< HEAD
	@RequestMapping(ADD_TASK_URL)
	public ResponseEntity<Task> addTask(@RequestBody Task task, HttpServletRequest request){
		
		Task newTask = new Task();
		newTask.setCardId(task.getCardId());
		newTask.setInfo(task.getInfo());
		newTask.setStatus(task.getStatus());
		newTask.setTaskId(task.getTaskId());
		taskRepo.save(newTask);
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
	
	
=======

>>>>>>> d2cd4c8d88382030359b1190fa4b5709734a8db9
}