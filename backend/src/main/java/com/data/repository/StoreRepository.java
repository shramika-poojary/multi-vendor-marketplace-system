package com.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.Store;
import com.data.model.User;

public interface StoreRepository extends JpaRepository<Store,Integer> {
	
	Optional<Store> findByStoreId(Integer storeId);

	List<Store> findByVendor_Email(String email); // one vendor can have multiple stores so we use list here 

	List<Store> findByVendor_UserId(int userId);
	

}
