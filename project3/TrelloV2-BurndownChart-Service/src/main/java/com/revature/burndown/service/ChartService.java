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
	
	/**
	 * Calls the ChartRepo to get all Charts from the database.
	 * @return a List of Charts
	 */
	public List<Chart> findAll(){
		return chartRepo.findAll();
	}
	
	/**
	 * Calls the ChartRepo to get all Charts that belong to a specific Board from the database.
	 * @param chartBoard the id of the Board whose Charts are being pulled.
	 * @return a List of Charts that belong to a specific Board.
	 */
	public List<Chart> findByChartBoard(int chartBoard){
		return chartRepo.findByChartBoard(chartBoard);
	}

	/**
	 * Called to save a new Chart entry to the database.
	 * @param board the Chart to be saved.
	 * @return returns the saved Chart
	 */
	public Chart save(Chart board) {
		return chartRepo.save(board);
	}
}
