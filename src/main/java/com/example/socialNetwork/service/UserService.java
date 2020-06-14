package com.example.socialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username);
	}

	public boolean activateUser(String code) {
		User user = userRepo.findByActivated(code);
		if (user == null) {
			return false;
		}
		user.setActivated(null);
		userRepo.save(user);
		return true;
	}

	public void newPassword(User user, String oldpassword, String password) {
		User user1 = userRepo.findById((int)user.getId());
		if (user1.getPassword().equals(oldpassword)) {
			user1.setPassword(password);
			userRepo.save(user1);
		}
	}
	
	

}
