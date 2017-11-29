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

//
//	@Autowired
//	BoardRepo boardRepo;
//
//	@Autowired
//	LaneRepo laneRepo;
//
//	@Autowired
//	CardRepo cardRepo;
//
//	@Autowired
//	TaskRepo taskRepo;
//	
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
		System.out.println("delete Card listener");
		service.deleteCard(card);
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