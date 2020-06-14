package com.example.socialNetwork.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialNetwork.model.Post;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.UserRepository;
import com.example.socialNetwork.service.PostService;
import com.example.socialNetwork.service.UserService;

@RestController
public class ProfileController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;

	@PostMapping("/getprofile")
	public Map<String, Object> getprofile(@RequestBody Map<String, Integer> param, @AuthenticationPrincipal User user) {
		int idUser;
		if (param.get("iduser") != null) {
			idUser = (int) param.get("iduser");

		} else {
			idUser = user.getId();
		}
		Map<String, Object> model = new LinkedHashMap<>();
		User profile = userRepo.findById(idUser);
		if(profile != null) {
			model.put("id", profile.getId());
			model.put("name", profile.getName());
			model.put("famile", profile.getFamile());
			model.put("about", profile.getStatistics());
		}else {
			model.put("about", null);
		}

		return model;
	}

	@PostMapping("/newpost")
	public Map<String, Object> newpost(@RequestBody Post post, @AuthenticationPrincipal User user) {
		Map<String, Object> model = new LinkedHashMap<>();
		boolean done = postService.newpost(post, user);
		if (done) {
			model.put("status", true);
		} else {
			model.put("status", false);
		}
		return model;
	}
	
	@PostMapping("/getposts")
	public Post[] getpost(@RequestBody Map<String, Integer> param, @AuthenticationPrincipal User user) {
		Post[] posts = null;
		if(param.get("iduser") != null) {
			int a = (int)param.get("iduser");
			User user1 = userRepo.findById((int)user.getId());
			User user2 = userRepo.findById(a);
			posts = postService.getotherpost(user1, user2);

		}else {
			posts = postService.getpost(user);
		}
//		Map<String, Object> model = new LinkedHashMap<>();
		
		return posts;
	}
	
	@PostMapping("/getlenta")
	public Map<Integer, Object> getlenta( @AuthenticationPrincipal User user) {
		
	return postService.getlenta(user);
	}
	
	
	@PostMapping("/newpassword")
	public Map<String, Object> newpassword(@RequestBody Map<String, String> param, @AuthenticationPrincipal User user) {
		Map<String, Object> mpdel = new LinkedHashMap<String, Object>();
		userService.newPassword(user, param.get("oldpassword"), param.get("newpassword"));
		mpdel.put("status", true);
		return mpdel;
	}
	
	
	
	
	
}
