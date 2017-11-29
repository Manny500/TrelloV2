package com.revature.profile.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/*
 * Map multiple outputs to Source
 */
public interface Messaging {

	@Output("profileChannel")
	MessageChannel profileChannel();
	
}