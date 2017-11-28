package com.revature.burndown.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHART")
public class Chart {

	@Id
	@Column(name = "CHART_ID")
	private int chartId;

	@Column(name = "CHART_SUM")
	private int chartSum;

	@Column(name = "CHART_DATE")
	private String chartDate;
	
	@Column(name = "B_ID")
	private int chartBoard;

	public int getChartBoard() {
		return chartBoard;
	}

	public void setChartBoard(int chartBoard) {
		this.chartBoard = chartBoard;
	}

	public Chart() {

	}
	
	public Chart(int chartBoard, int chartSum) {
		this.chartBoard = chartBoard;
		this.chartSum = chartSum;
	}

	public int getChartId() {
		return chartId;
	}

	public void setChartId(int chartId) {
		this.chartId = chartId;
	}

	public int getChartSum() {
		return chartSum;
	}

	public void setChartSum(int chartSum) {
		this.chartSum = chartSum;
	}

	public String getChartDate() {
		return chartDate;
	}

	public void setChartDate(String chartDate) {
		this.chartDate = chartDate;
	}

}