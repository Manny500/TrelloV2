package com.revature.board.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.board.beans.Board;
import com.revature.board.repo.BoardRepo;

@RestController
public class BoardCtrl {
	
	
	@Autowired
	BoardRepo boardRepo;
	
	private final static String GET_BOARD_URL = "/home";
	@GetMapping(GET_BOARD_URL)
	public ResponseEntity<List<Board>> getBoards() {
		System.out.println("hello");
		List<Board> board = new ArrayList<Board>();
		
		board = boardRepo.findAll();
		
		
		return ResponseEntity.ok(board);
	}
	
	
}
