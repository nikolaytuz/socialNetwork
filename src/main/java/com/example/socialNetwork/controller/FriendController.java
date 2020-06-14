package com.example.socialNetwork.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.UserRepository;
import com.example.socialNetwork.service.FriendsService;

@RestController
public class FriendController {
	@Autowired
	FriendsService friendsService;
	@Autowired
	UserRepository userRepo;

	@PostMapping("/getfriends")
	public Map<Integer, Object> getfriends(@RequestBody Map<String, Integer> param,
			@AuthenticationPrincipal User user) {
		Map<Integer, Object> friends;
		if (param.get("iduser") == null) {
			friends = friendsService.getFriends(user);
		} else {
			User us = userRepo.findById((int) param.get("iduser"));
			friends = friendsService.getFriends(us);
		}

		return friends;
	}
	
	
	@PostMapping("/getalluser")
	public Map<Integer, Object> getallusers(@RequestBody Map<String, Integer> param,
			@AuthenticationPrincipal User user) {
		Map<Integer, Object> friends = new LinkedHashMap<>();
		User[] us = userRepo.findByUsernameNot(user.getUsername());
		for (int i = 0; i < us.length; i++) {
			if(us[i].getProfile().get("about") != null) {
				friends.put(i, us[i].getProfile());
			}
		}
		return friends;

	}

	@PostMapping("/addfriend")
	public Map<String, Object> addfriend(@RequestBody Map<String, Integer> param, @AuthenticationPrincipal User user) {
		Map<String, Object> model = new LinkedHashMap<>();
		User user2 = userRepo.findById((int) param.get("iduser"));
		User user1 = userRepo.findById((int)user.getId());
		friendsService.addFriend(user1, user2);

		model.put("status", true);
		return model;
	}
	
	@PostMapping("/dellfriend")
	public Map<String, Object> dellfriend(@RequestBody Map<String, Integer> param, @AuthenticationPrincipal User user) {
		Map<String, Object> model = new LinkedHashMap<>();
		User user2 = userRepo.findById((int) param.get("iduser"));
		User user1 = userRepo.findById((int)user.getId());
		friendsService.dellfriend(user1, user2);

		model.put("status", true);
		return model;
	}

	@PostMapping("/getparamfriend")
	public Map<Integer, Object> getparamfriend(@RequestBody Map<String, String> param,
			@AuthenticationPrincipal User user) {
		if(param.get("status").equals("subs")) {
			return friendsService.getSubs(user);
		}else {
			return friendsService.getParamFriend(user, (String) param.get("status"));
		}

	}

	@PostMapping("/itsfriend")
	public Map<String, Object> itsfriend(@RequestBody Map<String, Integer> param, @AuthenticationPrincipal User user) {
		Map<String, Object> model = new LinkedHashMap<>();
		model.put("status", friendsService.itsFriendOrSubs(user, userRepo.findById((int) param.get("iduser"))));
		return model;
	}
	
	@PostMapping("/setstatus")
	public Map<String, Object> setstatus(@RequestBody Map<String, String> param, @AuthenticationPrincipal User user) {
		Map<String, Object> model = new LinkedHashMap<>();
		User user1 = userRepo.findById((int)user.getId());
		User user2 = userRepo.findById(Integer.parseInt(param.get("iduser")));
		friendsService.setstatus(user1, user2, param.get("status"));
		model.put("status", true);
		return model;
	}


}
