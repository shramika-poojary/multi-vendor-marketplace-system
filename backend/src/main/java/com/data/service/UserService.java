package com.data.service;

import java.util.List;

import com.data.model.User;

public interface UserService {

	User registerUser(User user);
	
	User getUserById(int userId);
	
	List<User> getAllUsers();
	
	boolean deleteUser(int userId);
	
	User getUserByEmail(String email);
}
