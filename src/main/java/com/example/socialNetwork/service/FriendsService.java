package com.example.socialNetwork.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialNetwork.model.Friends;
import com.example.socialNetwork.model.Static;
import com.example.socialNetwork.model.User;
import com.example.socialNetwork.repository.FriendsRepository;
import com.example.socialNetwork.repository.StaticRepository;

@Service
public class FriendsService {
	@Autowired
	FriendsRepository friendsRepo;
	@Autowired
	StaticRepository staticRepo;

	public Map<Integer, Object> getFriends(User user) {
		Map<Integer, Object> ArrayFriend = new LinkedHashMap<>();
		Friends[] friends = friendsRepo.findByStatusNotAndFriendOneOrFriendTwoAndStatusNot("subs", user, user, "subs");
		for (int i = 0; i < friends.length; i++) {
			if ((int) friends[i].getFriendOne().get("id") != user.getId()) {
				ArrayFriend.put(i, friends[i].getFriendOne());
			} else if ((int) friends[i].getFriendTwo().get("id") != user.getId())
				ArrayFriend.put(i, friends[i].getFriendTwo());
		}
		return ArrayFriend;
	}
	
	public Map<Integer, Object> getIdFriends(User user) {
		Map<Integer, Object> ArrayFriend = new LinkedHashMap<>();
		Friends[] friends = friendsRepo.findByFriendOneOrFriendTwoAndStatusNot(user, user, "subs");
		for (int i = 0; i < friends.length; i++) {
			if ((int) friends[i].getFriendOne().get("id") != user.getId()) {
				ArrayFriend.put(i, friends[i].getFriendOne().get("id"));
			} else if ((int) friends[i].getFriendTwo().get("id") != user.getId())
				ArrayFriend.put(i, friends[i].getFriendTwo().get("id"));
		}
		return ArrayFriend;
	}

	public Map<Integer, Object> getParamFriend(User user, String status) {
		Map<Integer, Object> ArrayFriend = new LinkedHashMap<>();
		Friends[] friends = friendsRepo.findByFriendOneAndStatusOrFriendTwoAndStatus(user, status, user, status);
		for (int i = 0; i < friends.length; i++) {
			if ((int) friends[i].getFriendOne().get("id") != user.getId()) {
				ArrayFriend.put(i, friends[i].getFriendOne());

			} else
				ArrayFriend.put(i, friends[i].getFriendTwo());
		}
		return ArrayFriend;
	}

	public Map<Integer, Object> getSubs(User user) {
		Map<Integer, Object> ArrayFriend = new LinkedHashMap<>();
		Friends[] friends = friendsRepo.findByFriendTwoAndStatus(user, "subs");
		for (int i = 0; i < friends.length; i++) {
			if ((int) friends[i].getFriendOne().get("id") != user.getId()) {
				ArrayFriend.put(i, friends[i].getFriendOne());

			} else
				ArrayFriend.put(i, friends[i].getFriendTwo());
		}
		return ArrayFriend;
	}

	public void addFriend(User user1, User user2) {
		if (!this.itsFriend(user1, user2)) {
			Friends[] two = friendsRepo.findByFriendOneAndFriendTwo(user2, user1);
			if ((two.length > 0) && (two[0].getStatus().equals("subs"))) {
//				System.out.println("Добавил в др");
				two[0].setStatus("friend");
				friendsRepo.save(two[0]);
			} else {
				Friends[] one = friendsRepo.findByFriendOneAndFriendTwo(user1, user2);
				if (one.length == 0) {
					Friends friend = new Friends();
					friend.setFriendOne(user1);
					friend.setFriendTwo(user2);
					friend.setStatus("subs");
//					System.out.println("Новая запись");
					friendsRepo.save(friend);
				}

			}
			this.actualcountfriend(user1, user2);
		}
	}

	public void dellfriend(User user1, User user2) {
		Friends[] one = friendsRepo.findByFriendOneAndFriendTwo(user1, user2);
		if (one.length != 0 && one[0].getStatus().equals("subs")) {
			friendsRepo.delete(one[0]);
		}
		if (one.length != 0 && !one[0].getStatus().equals("subs")) {
			one[0].setFriendOne(user2);
			one[0].setFriendTwo(user1);
			friendsRepo.save(one[0]);
		}
		Friends[] two = friendsRepo.findByFriendOneAndFriendTwo(user2, user1);
		if (two.length != 0 && two[0].getStatus().equals("subs")) {
			friendsRepo.delete(two[0]);
		}
		if (two.length != 0 && !two[0].getStatus().equals("subs")) {
			two[0].setStatus("subs");
			friendsRepo.save(two[0]);
		}
		this.actualcountfriend(user1, user2);
	}

	public void actualcountfriend(User user1, User user2) {
		Static st1 = user1.getStatistics();
		Static st2 = user2.getStatistics();
		if (this.getFriends(user1) != null) st1.setFriend_count(this.getFriends(user1).size());
		if (this.getFriends(user2) != null) st2.setFriend_count(this.getFriends(user2).size());
		if (this.getSubs(user2) != null) st1.setSubscribe_count(this.getSubs(user1).size());
		if (this.getSubs(user2) != null) st2.setSubscribe_count(this.getSubs(user2).size());
		staticRepo.save(st1);
		staticRepo.save(st2);
	}

	public boolean itsFriend(User user1, User user2) {
		Friends[] one = friendsRepo.findByFriendOneAndFriendTwo(user1, user2);
		Friends[] two = friendsRepo.findByFriendOneAndFriendTwo(user2, user1);
		if ((one.length != 0 && (!one[0].getStatus().equals("subs")))
				|| ((two.length != 0) && !two[0].getStatus().equals("subs"))) {
			return true;
		}
		return false;
	}

	public Object itsFriendOrSubs(User user1, User user2) {
		Friends[] one = friendsRepo.findByFriendOneAndFriendTwo(user1, user2);
		Friends[] two = friendsRepo.findByFriendOneAndFriendTwo(user2, user1);
		if (one.length != 0) {
			if(one[0].getStatus().equals("subs"))
				return "subs";
			else
				return "friend";
		}
		if (two.length != 0) {
			if(two[0].getStatus().equals("subs"))
				return "subsyou";
			else
				return "friend";
		}
		return false;
	}

	public void setstatus(User user1, User user2, String status) {
		Friends[] one = friendsRepo.findByFriendOneAndFriendTwo(user1, user2);
		Friends[] two = friendsRepo.findByFriendOneAndFriendTwo(user2, user1);
		Friends friend = null;
		if (one.length != 0 && (!one[0].getStatus().equals("subs"))) {
			friend = one[0];
		}
		if ((two.length != 0) && !two[0].getStatus().equals("subs")) {
			friend = two[0];
		}
		friend.setStatus(status);
		friendsRepo.save(friend);

	}

}
