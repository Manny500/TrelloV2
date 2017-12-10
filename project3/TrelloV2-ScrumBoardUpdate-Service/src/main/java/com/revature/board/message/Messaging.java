package com.revature.board.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/*
 * Map multiple outputs to Source
 */
public interface Messaging {

	//RabbitMQ channel
	@Output("trellov2rabbitchannel")
	MessageChannel trellov2rabbitchannel();
	
	
	
}