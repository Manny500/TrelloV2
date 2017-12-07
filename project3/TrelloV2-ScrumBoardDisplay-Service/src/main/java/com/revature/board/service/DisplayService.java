package com.revature.board.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.board.beans.Board;
import com.revature.board.beans.Card;
import com.revature.board.beans.Lane;
import com.revature.board.beans.TV2User;
import com.revature.board.beans.Task;
import com.revature.board.repo.BoardRepo;
import com.revature.board.repo.CardRepo;
import com.revature.board.repo.LaneRepo;
import com.revature.board.repo.TaskRepo;

@Service
public class DisplayService {

	@Autowired
	private BoardRepo bRepo;
	@Autowired
	private LaneRepo lRepo;
	@Autowired
	private CardRepo cRepo;
	@Autowired
	private TaskRepo tRepo;
	
	private final RestTemplate restTemplate;
	
	public DisplayService(RestTemplate rest) {
		this.restTemplate = rest;
	}
	
	
	@HystrixCommand(fallbackMethod = "reliable", defaultFallback = "reliable")
	public ResponseEntity<TV2User> circuitTest(int id, String h1, String h2) {
		
		//TV2User test = new TV2User();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", h1);
		headers.set("Authorization", h2);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		URI uri = URI.create("http://localhost:8765/profile/hope");

		return this.restTemplate.exchange(uri, HttpMethod.GET, entity, TV2User.class);
	    //test = this.restTemplate.getForObject(uri, TV2User.class);
		
		//return test;
	}
	
	public ResponseEntity<TV2User> reliable(int id, String h1, String h2) {
		
		System.err.println("Here");
		TV2User test = new TV2User();
		
		test.setFirstName("Not Available");
		
		return ResponseEntity.ok(test);
	}
	

	//Board
	public List<Board> findAllBoard(){
		return bRepo.findAllByOrderByBIdAsc();
	}

	public Board saveBoard(Board board) {
		return bRepo.save(board);
	}
	
	public Board findBoardById(int bId) {
		return bRepo.findBybId(bId);
	}
	
	public void deleteBoard(Board board) {
		bRepo.delete(board);
	}
	
	//Lane
	public List<Lane> findAllLane(){
		return lRepo.findAllByOrderByLaneIdAsc();
	}
	public List<Lane> findByBoardId(int bId){
		return lRepo.findBybId(bId);
	}
	
	public Lane saveLane(Lane lane) {
		return lRepo.save(lane);
	}
	
	public void deleteLane(Lane lane) {
		lRepo.delete(lane);
	}
	
	//Card
	public List<Card> findAllCard(){
		return cRepo.findAllByOrderByCIdAsc();
	}
	
	public List<Card> findByLaneId(int laneId){
		return cRepo.findByLId(laneId);
	}
	
	public Card saveCard(Card card) {
		return cRepo.save(card);
	}

	public void deleteCard(Card card) {
		cRepo.delete(card);
	}
	
	//Task
	public List<Task> findAllTask(){
		return tRepo.findAllByOrderByTaskIdAsc();
	}
	
	public List<Task> findByCardId(int cardId){
		return tRepo.findByCardId(cardId);
	}
	
	public Task saveTask(Task task) {
		return tRepo.save(task);
	}
	public void deleteTask(Task task) {
		tRepo.delete(task);
	}

	/**
     * This setter method should be used only by unit tests.
     * @param personRepository
     */
	public void setBoardRepo (BoardRepo repo) {
		this.bRepo = repo;
	}
	public void setLaneRepo (LaneRepo repo) {
		this.lRepo = repo;
	}
	public void setCardRepo (CardRepo repo) {
		this.cRepo = repo;
	}
	public void setTaskRepo (TaskRepo repo) {
		this.tRepo = repo;
	}
	
}
