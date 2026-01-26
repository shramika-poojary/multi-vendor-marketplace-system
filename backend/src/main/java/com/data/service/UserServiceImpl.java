package com.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.data.enums.Role;
import com.data.exception.ResourceNotFoundException;
import com.data.model.User;
import com.data.repository.UserRepository;

import lombok.Data;

@Service
@Data
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public User registerUser(User user) {
		if (repo.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		if (user.getRole() == null) {
            user.setRole(Role.CUSTOMER);
        }
		
		return repo.save(user);
	}

	@Override
	public User getUserById(int userId){
		Optional<User> user = repo.findById(userId);
		if(!user.isEmpty()) {
			return user.get();
		}else {
			throw new ResourceNotFoundException("User Not Found");
			
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users=repo.findAll();

		return users;
	}

	@Override
	public boolean deleteUser(int userId) {
		Optional<User> user = repo.findById(userId);
		if(user.isPresent()) {
			repo.deleteById(userId);
			return true;
		}
		else {
			throw new ResourceNotFoundException("Resource Not Found");
		}
		
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> user=repo.findByEmail(email);
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new ResourceNotFoundException("User not found with email:"+email);
		}
		
	}

}
