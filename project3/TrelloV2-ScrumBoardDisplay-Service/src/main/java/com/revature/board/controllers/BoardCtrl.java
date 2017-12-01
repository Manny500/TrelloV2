package com.revature.board.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.board.beans.Board;
import com.revature.board.beans.Card;
import com.revature.board.beans.Lane;
import com.revature.board.beans.Task;
import com.revature.board.beans.cardDto;
import com.revature.board.service.DisplayService;

@EnableBinding(Sink.class)
@RestController
@EnableResourceServer
public class BoardCtrl {

	private final static String GET_BOARD_URL = "/home";
	private final static String GET_LANE_URL = "/trello";
	private final static String GET_USER_BOARD_URL = "/user-home";
	private final static String GET_CARD_URL = "/showCard";
	private final static String GET_TASK_URL = "/showTask";

	@Autowired
	DisplayService service;

	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 1")
	public void addBoard(@RequestBody Board board) {

		service.saveBoard(board);
	}

	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 2")
	public void addLane(@RequestBody Lane lane) {

		service.saveLane(lane);
	}

	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 3")
	public void addCard(@RequestBody Card card) {

		service.saveCard(card);
		
		
	}

	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 4")
	public void switchLane(@RequestBody Card card) {

		service.saveCard(card);
		
		
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 6")
	public void addTask(@RequestBody Task task) {

		service.saveTask(task);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 7")
	public void delTask(@RequestBody Task task) {

		service.deleteTask(task);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 8")
	public void delCard(@RequestBody Card card) {
		List<Task> tasks = service.findByCardId(card.getcId());
		for(Task t : tasks) {
			service.deleteTask(t);
		}
		service.deleteCard(card);
	}
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 9")
	public void delLane(@RequestBody Lane lane) {
		List<Card> cards = service.findByLaneId(lane.getLaneId());
			for(Card c : cards) {
				service.deleteCard(c);
			}

		
		service.deleteLane(lane);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 10")
	public void delBoard(@RequestBody Board board) {
		
		
		List<Lane> allLanes = service.findAllLane();
		
		List<Lane> boardLanes = new ArrayList<Lane>();
		
		for(int i = 0; i < allLanes.size(); i++) {
			
			if(allLanes.get(i).getbId() == board.getbId()) {
				
				boardLanes.add(allLanes.get(i));
			}
			
		}
		
		List<Card> allCards = service.findAllCard();
		
		List<Card> boardCards = new ArrayList<Card>();
		
		for(int b = 0; b < boardLanes.size(); b++) {
			
			for(int i = 0; i < allCards.size(); i++) {
				
				if(allCards.get(i).getlId() == boardLanes.get(b).getLaneId()) {
					
					boardCards.add(allCards.get(i));
					
				}
			}
		}
		
		List<Task> allTasks = service.findAllTask();
		
		List<Task> boardTasks = new ArrayList<Task>();
		
		for(int b = 0; b < boardCards.size(); b++) {
			
			for(int i = 0; i < allTasks.size(); i++) {
				
				if(allTasks.get(i).getCardId() == boardCards.get(b).getcId()) {
					
					boardTasks.add(allTasks.get(i));
					
				}
			}
		}
		
		for(int i = 0; i < boardTasks.size(); i++) {
			service.deleteTask(boardTasks.get(i));
		}
		
		for(int i = 0; i < boardCards.size(); i++) {
			service.deleteCard(boardCards.get(i));
		}
		
		for(int i = 0; i < boardLanes.size(); i++) {
			service.deleteLane(boardLanes.get(i));
		}
		
		service.deleteBoard(board);
	}
	
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 11")
	public void verCard(@RequestBody cardDto dto) {
		Card card = new Card();
		card.setcId(dto.getcId());
		card.setcVerify(dto.getcVerify());
		card.setlId(dto.getlId());
		card.setcWorth(dto.getcWorth());
		card.setcDescription(dto.getcDescription());
		card.setcTitle(dto.getcTitle());
		
		service.saveCard(card);
		
		Board board = new Board();
		board.setbId(dto.getbId());
		board.setTv2Id(dto.getTv2Id());
		board.setbTotal(dto.getbTotal() - dto.getcWorth());
		board.setbTitle(dto.getbTitle());
		board.setTv2Team(dto.getTv2Team());
		
		service.saveBoard(board);
		
		
		
	}
	

	@GetMapping(GET_BOARD_URL)
	public ResponseEntity<List<Board>> getBoards() {

		List<Board> board = new ArrayList<Board>();

		board = service.findAllBoard();

		return ResponseEntity.ok(board);
	}

	@GetMapping(GET_USER_BOARD_URL)
	public ResponseEntity<List<Board>> getUserBoards() {

		List<Board> board = new ArrayList<Board>();

		board = service.findAllBoard();

		return ResponseEntity.ok(board);
	}

	@GetMapping(GET_LANE_URL)
	public ResponseEntity<List<Lane>> getLanes() {
		
		List<Lane> lane = new ArrayList<Lane>();

		lane = service.findAllLane();

		return ResponseEntity.ok(lane);
	}

	@GetMapping(GET_CARD_URL)
	public ResponseEntity<List<Card>> getCards() {
		
		List<Card> card = new ArrayList<Card>();

		card = service.findAllCard();

		return ResponseEntity.ok(card);
	}

	@GetMapping(GET_TASK_URL)
	public ResponseEntity<List<Task>> getTasks() {
		
		List<Task> task = new ArrayList<Task>();

		task = service.findAllTask();

		return ResponseEntity.ok(task);
	}

}