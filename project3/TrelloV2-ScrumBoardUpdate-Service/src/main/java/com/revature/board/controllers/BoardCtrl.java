package com.revature.board.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.board.message.Messaging;

@RestController
public class BoardCtrl {
	private final static String ADD_TASK_URL = "/addTask";
	private final static String ADD_CARD_URL = "/addCard";
	private final static String ADD_LANE_URL = "/addLane";
	private final static String ADD_BOARD_URL = "/addBoard";
	private final static String SWITCH_LANE_URL = "/updateCard";
	private final static String UPDATE_BURNDOWN_URL = "/updateBurndown";
	private final static String DELETE_TASK_URL = "/deleteTask";
	private final static String DELETE_CARD_URL = "/deleteCard";

	@Autowired
	Messaging mysource;
	
	@RequestMapping(ADD_TASK_URL)
	public String addTask(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.fcMessagePlace1().send(MessageBuilder.withPayload(payload).setHeader("micro", 6).build());

		return "Success";
	}
	
	@RequestMapping(UPDATE_BURNDOWN_URL)
	public String updateBurndown(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.fcMessagePlace1().send(MessageBuilder.withPayload(payload).setHeader("micro", 5).build());

		return "Success";
	}
	
	@RequestMapping(SWITCH_LANE_URL)
	public String switchLane(@RequestBody String payload, HttpServletRequest request) {

		mysource.fcMessagePlace1().send(MessageBuilder.withPayload(payload).setHeader("micro", 4).build());

		return "Success";
	}

	@RequestMapping(ADD_CARD_URL)
	public String addCard(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.fcMessagePlace1().send(MessageBuilder.withPayload(payload).setHeader("micro", 3).build());

		return "Success";
	}

	@RequestMapping(ADD_LANE_URL)
	public String addlane(@RequestBody String payload, HttpServletRequest request) {

		mysource.fcMessagePlace1().send(MessageBuilder.withPayload(payload).setHeader("micro", 2).build());

		return "Success";
	}

	@RequestMapping(ADD_BOARD_URL)
	public String addBoard(@RequestBody String payload, HttpServletRequest request) {

		mysource.fcMessagePlace1().send(MessageBuilder.withPayload(payload).setHeader("micro", 1).build());

		return "Success";
	}
	
	@RequestMapping(DELETE_TASK_URL)
	public String deleteTask(@RequestBody String payload, HttpServletRequest request) {
		
		
		mysource.fcMessagePlace1().send(MessageBuilder.withPayload(payload).setHeader("micro", 7).build());

		return "Success deleted card";
	}
	@RequestMapping(DELETE_CARD_URL)
	public String deleteCard(@RequestBody String payload, HttpServletRequest request) {
		
		System.out.println("----------------------" + payload +"------------------");
		mysource.fcMessagePlace1().send(MessageBuilder.withPayload(payload).setHeader("micro", 8).build());

		return "Success deleted card";
	}
	
	

}
