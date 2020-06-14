package com.example.socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.socialNetwork.model.Post;
import com.example.socialNetwork.model.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PostRepository extends CrudRepository<Post, Integer> {
	Post[] findAllByUserOrderByCreatedDesc(User user);

	Post findById(int idpost);

	Post[] findAllByUserAndOnlyFriendsOrderByCreatedDesc(User user2, String only);
}