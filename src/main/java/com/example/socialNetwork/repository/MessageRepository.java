package com.example.socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.socialNetwork.model.Message;
import com.example.socialNetwork.model.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MessageRepository extends CrudRepository<Message, Integer> {

	Message[] findByUserOneAndUserTwoOrUserOneAndUserTwoOrderByCreatedAsc(User user1, User user2, User user22, User user12);

	Message[] findByUserOneAndItsnewOrUserTwoAndItsnew(User user1, String string, User user12, String string2);

	Message[] findByUserOneAndUserTwoAndItsnewOrUserOneAndUserTwoAndItsnew(User user1, User user2, String string,
			User user22, User user12, String string2);

	Message[] findByUserOneOrUserTwoOrderByCreatedAsc(User user1, User user12);

//	Message[] findDistinctByUserOneOrUserTwoOrderByCreatedAsc(User user1, User user12);

}
