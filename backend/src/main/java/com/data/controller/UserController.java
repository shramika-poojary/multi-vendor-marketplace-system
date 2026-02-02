package com.data.controller;

import java.util.List;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.model.Product;
import com.data.model.Store;
import com.data.model.User;
import com.data.service.ProductServiceImpl;
import com.data.service.StoreServiceImpl;
import com.data.service.UserServiceImpl;
@CrossOrigin(origins="http://localhost:5173",
allowCredentials = "true")
@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	
	@PreAuthorize("hasAnyRole('USER','VENDOR','ADMIN')")
	@GetMapping("/{userId}")
	public ResponseEntity<?> get_user_by_id(@PathVariable int userId){
		User user=userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('USER','VENDOR','ADMIN')")
	@GetMapping("/profile")
	public ResponseEntity<User> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
	    String username = userDetails.getUsername();
	    User user = userService.getUserByEmail(username);
	    return ResponseEntity.ok(user);
	}

	
	@PreAuthorize("hasAnyRole('USER','VENDOR','ADMIN')")
	@GetMapping("/email/{email}")
	public ResponseEntity<?> get_user_by_email(@PathVariable String email){
		User user=userService.getUserByEmail(email);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	
	//update 
	// later
	//delete
	
	
}
