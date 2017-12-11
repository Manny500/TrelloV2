package com.revature.board.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	

	/**
	 * Circuit Breaker--
	 * Circuit Breaker pattern prevents attempts of an application to perform an operation that is likely
	 * to fail, that allows the application to continue working without wasting critical resources while
	 * problem is not resolved. The pattern can also detect whether the problem is resolved, and allows the
	 * application to repeat operation.
	 * 
	 * @param h1
	 * @param h2
	 * @return RestTemplate
	 */
  @SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "reliable", defaultFallback = "reliable")
	public ResponseEntity<List<TV2User>> circuitTest(String h1, String h2) {
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", h1);
		headers.set("Authorization", h2);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		URI uri = URI.create("http://localhost:8765/profile/circuitMessage");
		
		return (ResponseEntity<List<TV2User>>) this.restTemplate.exchange(uri, HttpMethod.GET, entity, (Class<? extends ArrayList<TV2User>>)ArrayList.class);
	    
	}
	
	public ResponseEntity<List<TV2User>> reliable(String h1, String h2) {
		
		List<TV2User> list = new ArrayList<TV2User>();
		TV2User test = new TV2User();
		
		test.setFirstName("Profile Service Not Available");
		list.add(test);
		
		return ResponseEntity.ok(list);
	}
	

	//Board
	public List<Board> findAllBoard(){
		return bRepo.findAllByOrderByBIdDesc();
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
		return lRepo.findAllByOrderByLaneIdDesc();
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
		return cRepo.findAllByOrderByCIdDesc();
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
		return tRepo.findAllByOrderByTaskIdDesc();
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
