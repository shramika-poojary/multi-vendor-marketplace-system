package com.data.service;

import java.util.List;

import com.data.dto.StoreResponseDTO;
import com.data.model.Store;

public interface StoreService {

	Store createStore(Store store,String email);
	Store getStoreById(int storeId);
	List<StoreResponseDTO> getAllStore();
	List<StoreResponseDTO> getStoreByVendor(String email);
}
