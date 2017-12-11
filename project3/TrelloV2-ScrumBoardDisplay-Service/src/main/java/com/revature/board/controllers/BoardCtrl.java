package com.revature.board.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.board.beans.Board;
import com.revature.board.beans.BurndownDto;
import com.revature.board.beans.Card;
import com.revature.board.beans.Lane;
import com.revature.board.beans.TV2User;
import com.revature.board.beans.Task;
import com.revature.board.beans.cardDto;
import com.revature.board.service.DisplayService;

@EnableCircuitBreaker
@EnableBinding(Sink.class)
@RestController
@EnableResourceServer
public class BoardCtrl {

	private final static String GET_BOARD_URL = "/home";
	private final static String GET_LANE_URL = "/trello";
	private final static String GET_CARD_URL = "/showCard";
	private final static String GET_TASK_URL = "/showTask";

	@Autowired
	DisplayService service;
	
	/**
	 * 
	 * @param builder - RestTemplateBuilder used to create a RestTemplate
	 * @return - a configured rest template instance.
	 */
	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
	    return builder.build();
	}
	
	/**
	 * When user logs in, make call to profile service to check if profile service is available.
	 * If unavailable, send back a message saying profile is unavailable. 
	 * @param request - HttpServletRequest (comes from client-side Angular). 
	 * @return - 
	 */
	@GetMapping("/circuit")
	public ResponseEntity<List<TV2User>> circuit(HttpServletRequest request) {

		
		return service.circuitTest(request.getHeader("Content-Type"), request.getHeader("Authorization"));
		
	}
	
	
	/**
	 * Waiting/listening for rabbitmq to send a board object (comes from client-side Angular)
	 * @param board - a Board object that comes from the stream. 
	 * 	Adds board to database.
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 1")
	public void addBoard(@RequestBody Board board) {

		service.saveBoard(board);
	}

	/**
	 * Waiting/listening for rabbitmq to send a lane object (comes from client-side Angular)
	 * @param lane - a Lane object that comes from the stream. 
	 * 	Adds lane to database.
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 2")
	public void addLane(@RequestBody Lane lane) {

		service.saveLane(lane);
	}

	/**
	 * Waiting/listening for rabbitmq to send a card object (comes from client-side Angular)
	 * @param card - a Card object that comes from the stream.
	 * 	 Adds card to database.
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 3")
	public void addCard(@RequestBody Card card) {

		service.saveCard(card);
	}
	
	
	/**
	 * Waiting/listening for rabbitmq to send a card object (comes from client-side Angular)
	 * @param card - a Card object that comes from the stream. 
	 * 	Updates which lane the card is in. 
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 4")
	public void switchLane(@RequestBody Card card) {

		service.saveCard(card);
	}
	
	
	/**
	 * Waiting/listening for rabbitmq to send a data transfer object(dto) of burndownchart information (comes from client-side Angular).
	 * @param dto - a data transfer object containing information regarding the update of the burndownchart. 
	 * 	Calculate the new point value of what the burndown chart will display(y-axis value) 
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 5")
	public void updateBurndown(@RequestBody BurndownDto dto) {
		Board board = service.findBoardById(dto.getbId());
		
		
		
		int newSum = 0;
		
		for(Lane l: dto.getLanes()) {
			for(Card c: dto.getCards()) {
				if(dto.getbId() == l.getbId() && l.getLaneId() == c.getlId() && c.getcVerify() == 0) {
					newSum += c.getcWorth();
				}
			}
		}
	
		board.setbTotal(newSum);
		
		service.saveBoard(board);
	}
	
	/**
	 * Waiting/listening for rabbitmq to send a task object (comes from client-side Angular)
	 * @param task - a Task object that comes from the stream.
	 * 	 Adds the task to the database. 
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 6")
	public void addTask(@RequestBody Task task) {

		service.saveTask(task);
	}
	
	/**
	 * Waiting/listening for rabbitmq to send a task object (comes from client-side Angular)
	 * @param task - a Task object that comes from the stream. Deletes the task from the database. 
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 7")
	public void delTask(@RequestBody Task task) {

		service.deleteTask(task);
	}
	
	/**
	 * Waiting/listening for rabbitmq to send a card object (comes from client-side Angular)
	 * @param card - a Card object that comes from the stream. 
	 * 	Deletes the card from the database. 
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 8")
	public void delCard(@RequestBody Card card) {
		List<Task> tasks = service.findByCardId(card.getcId());
		for(Task t : tasks) {
			service.deleteTask(t);
		}
		service.deleteCard(card);
	}
	
	
	/**
	 * Waiting/listening for rabbitmq to send a lane object (comes from client-side Angular)
	 * @param lane - a Lane object that comes from the stream. 
	 * 	Deletes the lane from the database. 
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 9")
	public void delLane(@RequestBody Lane lane) {
		List<Card> cards = service.findByLaneId(lane.getLaneId());
			for(Card c : cards) {
				service.deleteCard(c);
			}

		
		service.deleteLane(lane);
	}
	
	
	/**
	 * Waiting/listening for rabbitmq to send a board object (comes from client-side Angular)
	 * @param board - a Board object that comes from the stream. 
	 * 	Deletes the board from the database.
	 * 	- If board has any lanes/cards/tasks associated with it, it first deletes all tasks, then all cards,
	 * 		and then all the lanes before finally deleting the board.
	 */
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
	
	/**
	 * Waiting/listening for rabbitmq to send a card object (comes from client-side Angular)
	 * @param card - a Card object that comes from the stream. 
	 * 	Updates the card in the database to change.
	 */
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
	

	/**
	 * 
	 * @return - list of all the boards in the database. 
	 */
	@GetMapping(GET_BOARD_URL)
	public ResponseEntity<List<Board>> getBoards() {

		List<Board> board = new ArrayList<Board>();

		board = service.findAllBoard();

		return ResponseEntity.ok(board);
	}


	/**
	 * 
	 * @return - list of all the lanes in the database.
	 */
	@GetMapping(GET_LANE_URL)
	public ResponseEntity<List<Lane>> getLanes() {
		
		List<Lane> lane = new ArrayList<Lane>();

		lane = service.findAllLane();

		return ResponseEntity.ok(lane);
	}

	/**
	 * 
	 * @return - list of all the cards.
	 */
	@GetMapping(GET_CARD_URL)
	public ResponseEntity<List<Card>> getCards() {
		
		List<Card> card = new ArrayList<Card>();

		card = service.findAllCard();

		return ResponseEntity.ok(card);
	}

	/**
	 * 
	 * @return - list of all the tasks.
	 */
	@GetMapping(GET_TASK_URL)
	public ResponseEntity<List<Task>> getTasks() {
		
		List<Task> task = new ArrayList<Task>();

		task = service.findAllTask();

		return ResponseEntity.ok(task);
	}

}