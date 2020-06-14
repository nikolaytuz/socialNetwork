package com.example.socialNetwork.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.UserRepository;

@Controller
public class GreetingController {
	@Autowired
	UserRepository userRepo;

	@GetMapping("/")
	public String greeting(Map<String, Object> model) {
		return "greeting";
	}

	@GetMapping("/changepass/{code}")
	public String shangepass(Map<String, String> model, @PathVariable String code, HttpServletResponse httpServletResponse)
			throws IOException {
		User user = userRepo.findByChangepassword(code);
		model.put("code", code);
		if (user != null)
			return "changepass";
		else
			httpServletResponse.sendRedirect("/");
		return null;
	}

}