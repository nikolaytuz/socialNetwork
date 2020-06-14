package com.example.socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.socialNetwork.model.Comment;
import com.example.socialNetwork.model.Post;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CommentRepository extends CrudRepository<Comment, Integer> {
	
	Comment[] findAllByPostOrderByCreatedDesc(Post post);
	
}