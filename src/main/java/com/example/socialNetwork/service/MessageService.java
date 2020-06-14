package com.example.socialNetwork.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialNetwork.model.Message;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	MessageRepository messageRepo;

	public Message[] getMessage(User user1, User user2) {
		Message[] mess = messageRepo.findByUserOneAndUserTwoOrUserOneAndUserTwoOrderByCreatedAsc(user1, user2, user2,
				user1);
//			this.changeMessage(user1, user2);
		return mess;
	}

	public Map<Integer, Object> getDialogs(User user1) {
		Message[] mess = messageRepo.findByUserOneOrUserTwoOrderByCreatedAsc(user1, user1);
		Map<Integer, Object> model = new LinkedHashMap<>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < mess.length; i++) {
			if (mess[i].getUserOne().get("id") != user1.getId()) {
				if (arr.indexOf((Integer) mess[i].getUserOne().get("id")) == -1) {
					model.put(i, mess[i].getUserOne());
					arr.add((Integer) mess[i].getUserOne().get("id"));
				}
			} else {
				if (arr.indexOf((Integer) mess[i].getUserTwo().get("id")) == -1) {
					model.put(i, mess[i].getUserTwo());
					arr.add((Integer) mess[i].getUserTwo().get("id"));
				}
			}
		}
//			this.changeMessage(user1, user2);
		return model;
	}

	public Message[] getNewMessage(User user1) {
		Message[] mess = messageRepo.findByUserOneAndItsnewOrUserTwoAndItsnew(user1, "true", user1, "true");
		return mess;
	}

	public void changeMessage(User user1, User user2) {
		Message[] mess = messageRepo.findByUserOneAndUserTwoAndItsnewOrUserOneAndUserTwoAndItsnew(user1, user2, "true",
				user2, user1, "true");
		for (int i = 0; i < mess.length; i++) {
			if (mess[i].getItsnew().equals("true")) {
				mess[i].setItsnew("false");
				messageRepo.save(mess[i]);
			}
		}
	}

	public boolean sendMessage(User user1, User user2, String message) {
		Message mess = new Message();
		mess.setText(message);
		mess.setUserOne(user1);
		mess.setUserTwo(user2);
		messageRepo.save(mess);
		return true;
	}
}
