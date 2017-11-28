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
	private final static String ADD_CARD_URL = "/addCard";
	private final static String ADD_LANE_URL = "/addLane";
	private final static String ADD_BOARD_URL = "/addBoard";
	private final static String SWITCH_LANE_URL = "/updateCard";

	@Autowired
	Messaging mysource;
	
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

}
