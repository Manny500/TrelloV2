package com.revature.board.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.board.message.Messaging;

@RestController
@EnableResourceServer
//Controller for main SCRUM board update action 
public class BoardCtrl {
	
	private final static String ADD_TASK_URL = "/addTask";
	private final static String ADD_CARD_URL = "/addCard";
	private final static String ADD_LANE_URL = "/addLane";
	private final static String ADD_BOARD_URL = "/addBoard";
	private final static String SWITCH_LANE_URL = "/updateCard";
	private final static String UPDATE_BURNDOWN_URL = "/updateBurndown";
	private final static String DELETE_TASK_URL = "/deleteTask";
	private final static String DELETE_CARD_URL = "/deleteCard";
	private final static String DELETE_LANE_URL = "/deleteLane";
	private final static String DELETE_BOARD_URL = "/deleteBoard";
	private final static String VERFIY_CARD_URL = "/verifyCard";
	
	@Autowired
	Messaging mysource; //RabbitMQ
		
	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 6
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(ADD_TASK_URL)
	public String addTask(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 6).build());

		return "Success";
	}
	
	/**
	 * Send the message through RabbitMQ to BurndownChart-Service, channel 5
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(UPDATE_BURNDOWN_URL)
	public String updateBurndown(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 5).build());

		return "Success";
	}
	
	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 4
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(SWITCH_LANE_URL)
	public String switchLane(@RequestBody String payload, HttpServletRequest request) {

		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 4).build());

		return "Success";
	}

	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 3
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(ADD_CARD_URL)
	public String addCard(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 3).build());

		return "Success";
	}

	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 2
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(ADD_LANE_URL)
	public String addlane(@RequestBody String payload, HttpServletRequest request) {

		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 2).build());

		return "Success";
	}

	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 1
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(ADD_BOARD_URL)
	public String addBoard(@RequestBody String payload, HttpServletRequest request) {

		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 1).build());

		return "Success";
	}
	
	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 7
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(DELETE_TASK_URL)
	public String deleteTask(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 7).build());

		return "Success deleted task";
	}
  
	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 8
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(DELETE_CARD_URL)
	public String deleteCard(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 8).build());

		return "Success deleted card";
	}

	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 9
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(DELETE_LANE_URL)
	public String deleteLane(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 9).build());

		return "Success deleted lane";
	}
	
	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 10
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(DELETE_BOARD_URL)
	public String deleteBoard(@RequestBody String payload, HttpServletRequest request) {
		
		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 10).build());

		return "Success deleted Board";
	}
	
	/**
	 * Send the message through RabbitMQ to ScrumBoardDisplay-Service, channel 11
	 * 
	 * @param payload
	 * @param request
	 * @return return "Success" to angular's subscribe
	 */
	@RequestMapping(VERFIY_CARD_URL)
	public String verifyCard(@RequestBody String payload, HttpServletRequest request) {
		
		//message to display-microservice
		mysource.trellov2rabbitchannel().send(MessageBuilder.withPayload(payload).setHeader("micro", 11).build());

		return "Success verify card";
	}

}