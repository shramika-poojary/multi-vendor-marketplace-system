package com.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
	
	List<Product> findByStoreStoreId(Integer storeId); //coz one store can have multiple products

}
