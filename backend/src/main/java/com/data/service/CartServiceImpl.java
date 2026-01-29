package com.data.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.enums.Role;
import com.data.exception.ResourceNotFoundException;
import com.data.model.Cart;
import com.data.model.User;
import com.data.repository.CartRepository;
import com.data.repository.UserRepository;

import lombok.Data;

@Service
@Data
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	

	@Override
	public Cart createCart(int customerId) {
		User customer = userRepo.findById(customerId)
	            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

	    if (!customer.getRole().equals(Role.CUSTOMER)) {
	        throw new IllegalArgumentException("User is not a customer");
	    }

	    return repo.findByCustomerUserId(customerId)
	    		.orElseGet(()->{
	    			Cart cart = new Cart();
	    		    cart.setCustomer(customer);
	    		    return repo.save(cart);
	    		});
	
	}

	@Override
	public Cart getCartByUser(int customerId) {
		Optional<Cart> cart = repo.findByCustomerUserId(customerId);
		if(cart.isPresent()) {
			return cart.get();
		}else {
			throw new ResourceNotFoundException("Cart not found for this customer");
		}
	
	}

	@Override
	public boolean clearCart(int customerId) {
		Optional<Cart> cart = repo.findByCustomerUserId(customerId);
		if(cart.isPresent()) {
			cart.get().getCartItems().clear();
			repo.save(cart.get());
			return true;
			
		}else {
			throw new ResourceNotFoundException("Cart not found for this customer");
		}
	}

	@Override
	public int getCartId(int userId) {
		Cart cart = repo.findByCustomerUserId(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
	    return cart.getCartId();
	}
}
