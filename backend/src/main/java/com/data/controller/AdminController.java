package com.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.model.User;
import com.data.service.UserServiceImpl;
@CrossOrigin(origins="http://localhost:5173",
allowCredentials = "true")
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("api/admin")
public class AdminController {

	@Autowired
	private UserServiceImpl userService;
	
	
	
	@GetMapping("/users")
	public ResponseEntity<?> get_all_users(){
		List<User> user=userService.getAllUsers();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/users/email/{email}")
	public ResponseEntity<?> get_user_by_email(@PathVariable String email){
		User user=userService.getUserByEmail(email);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	 
	
}
