package com.example.socialNetwork.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialNetwork.model.Comment;
import com.example.socialNetwork.model.Post;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.service.PostService;

@RestController
public class PostController {
	@Autowired
	public PostService postService;
	
	
	@PostMapping("/putlike")
	public Map<String, Object> putLike(@RequestBody Map<String, Object> param, @AuthenticationPrincipal User user) {
		Map<String, Object> model = new LinkedHashMap<>();
		boolean otv = postService.putLike((int)param.get("idpost"), user);
		model.put("status", otv);
		return model;
	}
	
	@PostMapping("/getcoments")
	public Comment[] getComents(@RequestBody Map<String, Object> param) {
		return postService.getComents((int)param.get("idpost"));
	}
	
	@PostMapping("/newcomment")
	public boolean newcomment(@RequestBody Map<String, Object> param, @AuthenticationPrincipal User user) {
		return postService.newcomment((int)param.get("idpost"), (String)param.get("text"), user);
	}
	
	
}
