package com.revature.board.update;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Sink.class)
@RestController
public class Updates {

	/*
	 *  Multiple methods polling & web - Step 2 & 3
	 */
	@StreamListener(target=Sink.INPUT, condition="headers['speed'] > 40")
	public void logfast(String msg) {
		System.out.println("fast = " + msg);
		//return msg;
	}
	
	@StreamListener(target=Sink.INPUT, condition="headers['speed'] <= 40")
	public void logslow(String msg) {
		System.out.println("slow = " + msg);
		//return msg;
	}
}
