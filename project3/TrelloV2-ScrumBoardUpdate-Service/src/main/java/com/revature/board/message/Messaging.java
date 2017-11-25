package com.revature.board.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/*
 * Map multiple outputs to Source
 */
public interface Messaging {

	@Output("flashcardmessage1")
	MessageChannel fcMessagePlace1();
	
	
	@Output("flashcardmessage2")
	MessageChannel fcMessagePlace2();
}