package com.example.socialNetwork.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.example.socialNetwork.model.Message;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.UserRepository;
import com.example.socialNetwork.service.MessageService;
import com.example.socialNetwork.util.SenderSocket;

@RestController
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	SenderSocket senderSocket;

	@PostMapping("/getmessage")
	public Message[] getmessage(@RequestBody Map<String, Integer> param, @AuthenticationPrincipal User user1) {
		User user2 = userRepo.findById((int) param.get("iduser"));
		Message[] message = messageService.getMessage(user1, user2);

		return message;
	}

	@PostMapping("/getdiaogs")
	public Map<Integer, Object> getdiaogs(@AuthenticationPrincipal User user1) {
		Map<Integer, Object> message = messageService.getDialogs(user1);
		return message;
	}

	@PostMapping("/changemessage")
	public Map<String, Object> changemessage(@RequestBody Map<String, Integer> param,
			@AuthenticationPrincipal User user1) {
		User user2 = userRepo.findById((int) param.get("iduser"));
		messageService.changeMessage(user1, user2);
		Map<String, Object> model = new LinkedHashMap<>();
		model.put("status", "messreading");
		senderSocket.sendToUser(user2 .getUsername(), model);

		return model;
	}

	@PostMapping("/sendmessage")
	public Object sendmessage(@RequestBody Map<String, String> param, @AuthenticationPrincipal User user1) {
		User user2 = userRepo.findById(Integer.parseInt(param.get("iduser")));
		boolean status = messageService.sendMessage(user1, user2, param.get("message"));
		Map<String, Object> model = new LinkedHashMap<>();
		model.put("status", "messnew");
		senderSocket.sendToUser(user2 .getUsername(), model);
		
		return new LinkedHashMap<>().put("status", status);
	}

}
