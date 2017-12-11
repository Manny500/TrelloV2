package com.revature.burndown.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.burndown.bean.Board;
import com.revature.burndown.bean.BurndownDto;
import com.revature.burndown.bean.Card;
import com.revature.burndown.bean.Chart;
import com.revature.burndown.bean.Lane;
import com.revature.burndown.bean.cardDto;
import com.revature.burndown.repo.ChartRepo;
import com.revature.burndown.service.ChartService;

@EnableBinding(Sink.class)
@RestController
@EnableResourceServer
public class ChartData {

	@Autowired
	ChartRepo chartRepo;
	
	@Autowired
	ChartService service;
	
	ArrayList<Chart> list;
	
	private final static String POST_FACEUSER_URL = "/data";
	
	/**
	 * Called onInit() in the Burndown Chart component.ts file.
	 * Returns a list of charts that belong to that Board.
	 * @param board a Board object that is used to find relevant Charts.
	 * @return a List of Charts
	 * 
	 */
	@RequestMapping(POST_FACEUSER_URL)
	public ResponseEntity<ArrayList<Chart>> getBoardCharts(@RequestBody Board board) {
				
		list = (ArrayList<Chart>) service.findByChartBoard(board.getBoardId());
					
		return ResponseEntity.ok(list);
	}
	
	/**
	 * Called whenever a Card is added to a Board.
	 * Called from TrelloV2-ScrumBoardUpdate-Service updateBurndown method
	 * @param dto a BurndownDto that contains not only the Chart's data but also has the Lanes and Cards to calculate the current total
	 * @return void
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 5")
	public void updateBurndown(@RequestBody BurndownDto dto) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		
				
		Chart chart = new Chart();
		
		int newSum = 0;
		
		for(Lane l: dto.getLanes()) {
			for(Card c: dto.getCards()) {
				if(dto.getbId() == l.getbId() && l.getLaneId() == c.getlId() && c.getcVerify() == 0) {
					newSum += c.getcWorth();
				}
			}
		}
		
		chart.setChartBoard(dto.getbId());
		chart.setChartSum(newSum);
		chart.setChartDate(currentDate);
		
		service.save(chart);
		
	}
	
	/**
	 * When a Card is Verified (completed) this method is called to update the Burndown Chart with the new total.
	 * Called from TrelloV2-ScrumBoardUpdate-Service verifyCard method
	 * @param dto a CardDto that has both the Card's data and the Board's id and total
	 * @return void
	 */
	@StreamListener(target = Sink.INPUT, condition = "headers['micro'] == 11")
	public void verCard(@RequestBody cardDto dto) {
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		
				
		Chart chart = new Chart();
		
		int newSum = dto.getbTotal();
		
		newSum -= dto.getcWorth();
		
		chart.setChartBoard(dto.getbId());
		chart.setChartSum(newSum);
		chart.setChartDate(currentDate);
		
		service.save(chart);
		
		
	}
	
}