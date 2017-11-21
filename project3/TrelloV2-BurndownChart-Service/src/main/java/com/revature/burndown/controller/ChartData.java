package com.revature.burndown.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.burndown.bean.Board;
import com.revature.burndown.bean.Chart;
import com.revature.burndown.repo.ChartRepo;

@RestController
public class ChartData {

	@Autowired
	ChartRepo chartRepo;
	
	ArrayList<Chart> list;
	
	private final static String POST_FACEUSER_URL = "/data";
	
	@RequestMapping(POST_FACEUSER_URL)
	public ResponseEntity<ArrayList<Chart>> authenticateFaceuser(@RequestBody Board board) {
				
		list = (ArrayList<Chart>) chartRepo.findByChartBoard(board.getBoardId());
					
		return ResponseEntity.ok(list);
	}
}