package com.revature.burndown.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.burndown.bean.Chart;

@RestController
public class ChartData {

	private final static String POST_FACEUSER_URL = "/data";
	
	@RequestMapping("/data")
	public ResponseEntity<Chart> authenticateFaceuser() {
		
		System.out.println("i was called");
		
		Chart chart = new Chart();
			
		return ResponseEntity.ok(chart);
	}
}
