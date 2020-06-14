package com.example.socialNetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialNetwork.model.Static;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.StaticRepository;

@Service
public class StaticService {
	@Autowired
	private StaticRepository statRepo;
	
	
	public boolean newStatic(Static stat, User user){
		stat.setFriend_count(0);
		stat.setSubscribe_count(0);
		stat.setImage_count(0);
		stat.setUser(user);
		statRepo.save(stat);
		
		return true;
	}
	
	public void setStatic(Static statistics, User user) {
		statistics.setUser(user);
		statRepo.save(statistics);
	}
}
