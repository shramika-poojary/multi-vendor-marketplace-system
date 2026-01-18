package com.data.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {

	List<Address> findByUserUserId(Integer userId); //to find the address using user_id
}

