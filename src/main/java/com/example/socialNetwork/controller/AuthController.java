package com.example.socialNetwork.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialNetwork.model.Role;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.UserRepository;
import com.example.socialNetwork.service.MailSender;
import com.example.socialNetwork.service.UserService;

@RestController
public class AuthController {
	private final UserRepository userRepo;

	@Autowired
	public AuthController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	private MailSender mailSender;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/asd", method = RequestMethod.GET)
	public Iterable<User> login(@AuthenticationPrincipal User user, Map<String, Object> model) {
//		return user.getName();
		return this.userRepo.findAll();
	}
	
	@PostMapping("/testauth")
	public Map<String,Object> testAuth(@AuthenticationPrincipal User user){
		Map<String, Object> model = new LinkedHashMap<>();
		if (user == null) {
			model.put("status", false);
			return model;
		}else {
			model.put("status", true);
			return model;
		}
	}

	@PostMapping("/loginrest")
	@ResponseBody
	public Map<String, Object> performLogin(@RequestBody Map<String, Object> auth, HttpServletRequest request,
			HttpServletResponse response, @AuthenticationPrincipal User user) {
		Map<String, Object> model = new LinkedHashMap<>();
		String username = (String) auth.get("username");
		String password = (String) auth.get("password");
		System.out.println(user);
		if (user == null) {
			try {
				request.login(username, password);
				model.put("status", true);
				return model;
			} catch (Exception e) {
				model.put("status", false);
				model.put("error", "Bad Credentials");
				return model;
			}
		}else {
			model.put("status", true);
			model.put("error", "Ready now");
			return model;
		}

	}
	@PostMapping("/sendemail")
	public Map<String, Object> sendemail(@RequestBody Map<String, Object> param) {
		Map<String, Object> model = new LinkedHashMap<>();
		
		User user = userRepo.findByUsername((String)param.get("username"));

		if (!StringUtils.isEmpty(user.getEmail())) {

			String message = String.format(
					"Привет, %s вы зарегистрировали аккаунт в нашей социальной сети. \n" + "активируйте аккаунт "
							+ "\nhttp://localhost:8080/activate/%s"
							+ "\nЕсли вы ничего не делали проигнориуйте данное сообщение!",
					user.getName(), user.getActivated());
			mailSender.send(user.getEmail(), "Активация аккаунта", message);
		}
		model.put("status", true);
		return model;
	}
	
	@PostMapping("/newuser")
	public Map<String, Object> newuser(@RequestBody User user) {
		Map<String, Object> model = new LinkedHashMap<>();
		User userFromDb = userRepo.findByUsername(user.getUsername());

		if (userFromDb != null) {
			model.put("done", false);
			return model;
		}
		user.setRoles(Collections.singleton(Role.USER));
		user.setActivated(UUID.randomUUID().toString());
		user.setActive(true);
		userRepo.save(user);

		model.put("done", true);
		return model;
	}

	@GetMapping("/activate/{code}")
	public void activate(Model model, @PathVariable String code, HttpServletResponse httpServletResponse)
			throws IOException {
		boolean isActivated = userService.activateUser(code);
		if (isActivated) {
			model.addAttribute("message", "все хорошо");
		} else {
			model.addAttribute("message", "все плохо");
		}
		httpServletResponse.sendRedirect("/");
	}

}