package com.revature.burndown.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.burndown.bean.Chart;
import com.revature.burndown.repo.ChartRepo;

@Service
public class ChartService {

	@Autowired
	private ChartRepo chartRepo;
	
	public List<Chart> findAll(){
		return chartRepo.findAll();
	}
	
	public List<Chart> findByChartBoard(int chartBoard){
		return chartRepo.findByChartBoard(chartBoard);
	}

	public Chart save(Chart board) {
		return chartRepo.save(board);
	}
}
