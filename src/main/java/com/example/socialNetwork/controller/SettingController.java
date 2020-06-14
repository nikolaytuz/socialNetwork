package com.example.socialNetwork.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialNetwork.model.Static;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.service.StaticService;

@RestController
public class SettingController {
	@Autowired
	private StaticService staticService;

	@PostMapping("/newStatic")
	public Map<String, Object> setStatic(@RequestBody Static stat, @AuthenticationPrincipal User user) {
		Map<String, Object> model = new LinkedHashMap<>();
		boolean sucs = staticService.newStatic(stat, user);
		if (sucs) {
			model.put("done", true);
		} else {
			model.put("done", false);
		}
		return model;
	}

	@PostMapping("/setstatic")
	public Map<String, Object> settingschange(@RequestBody Static stat, @AuthenticationPrincipal User user) {
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		staticService.setStatic(stat, user);
		model.put("status", true);
		return model;
	}

}
