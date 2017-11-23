package com.revature.board.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.board.message.Messaging;

@RestController
public class UpdateBoard {

	@Autowired
	Messaging mysource;
	
	private final static String POST_UPDATE_BOARD_URL = "/updateBoard";

	@RequestMapping(POST_UPDATE_BOARD_URL)
	public String displayProfile(@RequestBody String payload) {

		Random r = new Random();
		System.out.println(payload);
		
		mysource.fcMessagePlace1()
				.send(MessageBuilder
					.withPayload(payload)
					.setHeader("speed", r.nextInt(8) * 10)
					.build());

			return "success";
		}

}
