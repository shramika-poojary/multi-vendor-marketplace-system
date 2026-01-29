package com.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.dto.StoreResponseDTO;
import com.data.model.Store;
import com.data.model.User;
import com.data.service.StoreServiceImpl;
@CrossOrigin(origins="http://localhost:5173",
				allowCredentials = "true")
@RestController
@RequestMapping("api/store")
public class StoreContoller {

	@Autowired
	private StoreServiceImpl service;
	
	@PreAuthorize("hasRole('VENDOR')")
	@PostMapping("stores")
	public ResponseEntity<?> create_store(@RequestBody Store store, Authentication authentication){
		String email=authentication.getName();
		Store store1=service.createStore(store, email);
		return new ResponseEntity(store1,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('VENDOR')")
	@GetMapping("get/stores/vendor")
	public ResponseEntity<?> get_store_by_vendorId(Authentication authentication){
		String email=authentication.getName();
		List<StoreResponseDTO> store=service.getStoreByVendor(email);
		return new ResponseEntity<>(store,HttpStatus.OK);
	}
	
	
	@GetMapping("/store/{storeId}")
	public ResponseEntity<?> get_store_by_id(@PathVariable int storeId){
		Store store=service.getStoreById(storeId);
		return new ResponseEntity<>(store,HttpStatus.OK);
	}
	
	

	@GetMapping("/stores")
	public ResponseEntity<?> get_all_stores(){
		List<StoreResponseDTO> store=service.getAllStore();
		return new ResponseEntity<>(store,HttpStatus.OK);
	}
	
}
