package com.example.socialNetwork.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.UserRepository;
import com.example.socialNetwork.service.MailSender;

@RestController
public class ShangePassword {
	@Autowired
	UserRepository userRepo;
	@Autowired
	MailSender mailSender;

	@PostMapping("/changepassword")
	public Map<String, Object> changepassword(@RequestBody Map<String, Object> param) {
		Map<String, Object> model = new LinkedHashMap<>();

		User user = userRepo.findByUsername((String) param.get("username"));
		if(user == null) {
			model.put("status", false);
			return model;
		}
		user.setChangepassword(UUID.randomUUID().toString());
		userRepo.save(user);

		if (!StringUtils.isEmpty(user.getEmail())) {

			String message = String.format(
					"Привет, %s. \n" + "Сбросить пароль можете по этой ссылке "
							+ "\nhttp://localhost:8080/changepass/%s"
							+ "\nЕсли вы ничего не делали проигнориуйте данное сообщение!",
					user.getName(), user.getChangepassword());
			mailSender.send(user.getEmail(), "Сброс пароля", message);
		}
		model.put("status", true);
		return model;
	}

	@PostMapping("/changepass/{code}")
	public void shangepasspost(Model model, @PathVariable String code, @RequestParam Map<String, Object> param,
			HttpServletResponse httpServletResponse) throws IOException {
		User user = userRepo.findByChangepassword(code);
		user.setPassword((String)param.get("password"));
		user.setChangepassword(null);
		userRepo.save(user);
		httpServletResponse.sendRedirect("/#/login");
	}

}
