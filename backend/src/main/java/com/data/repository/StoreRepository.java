package com.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.Store;

public interface StoreRepository extends JpaRepository<Store,Integer> {
	
	Optional<Store> findByStoreId(Integer storeId);

	List<Store> findByStoreVendorId(Integer vendorId); // one vendor can have multiple stores so we use list here 
	

}
