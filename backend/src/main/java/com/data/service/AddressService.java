package com.data.service;

import java.util.List;

import com.data.model.Address;

public interface AddressService {

	Address addAddress (Address address,int userId);
	
	Address updateAddress(Address address);
	
	List<Address> getAddressByUser(int userId);

}
