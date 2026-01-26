package com.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dto.StoreResponseDTO;
import com.data.exception.ResourceNotFoundException;
import com.data.model.Store;
import com.data.model.User;
import com.data.repository.StoreRepository;
import com.data.repository.UserRepository;

import lombok.Data;

@Service
@Data
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository repo;

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Store createStore(Store store, String email) {
		Optional<User> vendor=userRepo.findByEmail(email);
		if(vendor.isPresent()) {
			store.setVendor(vendor.get());
			return repo.save(store);
			
		}else {
			throw new ResourceNotFoundException("User Not Found");
		
		}
	
	}

	@Override
	public Store getStoreById(int storeId) {
		Optional<Store> store=repo.findById(storeId);
		if(store.isPresent()){
			return store.get();
		}else {
			throw new ResourceNotFoundException("Invalid StoreId");
		}
		
	}

	@Override
	public List<StoreResponseDTO> getAllStore() {
		List<Store> stores = repo.findAll();
		
		return stores.stream()
				.map(store-> new StoreResponseDTO(
						store.getStoreId(),
						store.getStoreName(),
						store.getStoreLogo(),
						store.getStoreDiscription()
						)).toList();
	}

	@Override
	public List<StoreResponseDTO> getStoreByVendor(String email) {
		User vendor = userRepo.findByEmail(email).orElseThrow();
		 List<Store> stores = repo.findByVendor_UserId(vendor.getUserId());
		 return stores.stream()
		            .map(store -> new StoreResponseDTO(
		                    store.getStoreId(),
		                    store.getStoreName(),
		                    store.getStoreLogo(),
		                    store.getStoreDiscription()
		            ))
		            .toList();
	}
	
	
}
