package com.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.model.Address;
import com.data.service.AddressServiceImpl;
@CrossOrigin(origins="http://localhost:5173",
allowCredentials = "true")
@RestController
@RequestMapping("api/user")
public class AddressContoller {

	@Autowired
	private AddressServiceImpl service;
	
	@PreAuthorize("hasAnyRole('CUSTOMER','VENDOR','ADMIN')")
	@PostMapping("/address/{userId}")
	public ResponseEntity<?> add_Address(@RequestBody Address address ,@PathVariable int userId){
		
		Address data=service.addAddress(address, userId);
		
		return new ResponseEntity<>(data,HttpStatus.CREATED);
		
	}
	
	//update address
	//later
	//delete address
	
	@GetMapping("get/addresses/{userId}")
	public ResponseEntity<?> getAddressesByUser(@PathVariable int userId){
		List<Address> addresses=service.getAddressByUser(userId);
		return new ResponseEntity<>(addresses,HttpStatus.OK);
	}
}
