package com.revature.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.board.beans.Board;
import com.revature.board.beans.Card;
import com.revature.board.beans.Lane;
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

	//Board
	public List<Board> findAllBoard(){
		return bRepo.findAll();
	}

	public Board saveBoard(Board board) {
		return bRepo.save(board);
	}
	
	//Lane
	public List<Lane> findAllLane(){
		return lRepo.findAll();
	}
	
	public Lane saveLane(Lane lane) {
		return lRepo.save(lane);
	}
	
	public void deleteLane(Lane lane) {
		lRepo.delete(lane);
	}
	
	//Card
	public List<Card> findAllCard(){
		return cRepo.findAll();
	}
	
	public Card saveCard(Card card) {
		return cRepo.save(card);
	}
	public void deleteCard(Card card) {
		cRepo.delete(card);
	}
	
	//Task
	public List<Task> findAllTask(){
		return tRepo.findAll();
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

	
}
