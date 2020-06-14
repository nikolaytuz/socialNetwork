package com.example.socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.socialNetwork.model.Friends;
import com.example.socialNetwork.model.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface FriendsRepository extends CrudRepository<Friends, Integer> {
	public Friends[] findByFriendOneOrFriendTwoAndStatusNot(User user, User user2, String status);
	
	public Friends[] findByFriendOneAndStatusOrFriendTwoAndStatus(User user, String status1, User user2, String status2);

	public Friends[] findByStatusNotAndFriendOneOrFriendTwoAndStatusNot(String string, User user, User user2, String string2);
	
	public Friends[] findByFriendOneAndFriendTwo(User user1, User user2);
	public Friends[] findByFriendTwoAndStatus(User user, String string);
}