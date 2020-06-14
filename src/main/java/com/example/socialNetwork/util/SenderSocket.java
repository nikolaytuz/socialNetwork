package com.example.socialNetwork.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


@Component
public class SenderSocket {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	
	public void sendToUser(String username, Map<String, Object> model) {
		
		simpMessagingTemplate.convertAndSendToUser(username, "/queue/alert", model);
	}

}
