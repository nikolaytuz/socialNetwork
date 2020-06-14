package com.example.socialNetwork.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.socialNetwork.model.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
	User findById(int id);
	User findByActivated(String code);
	User findByChangepassword(String code);
	User[] findByUsernameNot(String string);
}