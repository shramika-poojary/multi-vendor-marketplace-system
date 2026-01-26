package com.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.exception.ResourceNotFoundException;
import com.data.model.Address;
import com.data.model.User;
import com.data.repository.AddressRepository;
import com.data.repository.UserRepository;

import lombok.Data;
@Service
@Data
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository repo;
	
	@Autowired
	private UserRepository UserRepo;
	
	@Override
	public Address addAddress(Address address, int userId) {
		Optional<User> user = UserRepo.findById(userId);
		if(user.isPresent()) {
			address.setUser(user.get());
			Address UserAddress=repo.save(address);
			return UserAddress;
		}else {
			throw new ResourceNotFoundException("No such user");
		}
		
	}

	@Override
	public Address updateAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAddressByUser(int userId) {
		List<Address> address=repo.findByUserUserId(userId);
		return address;
	}

	
}
