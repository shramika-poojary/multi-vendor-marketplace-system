package com.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dto.CartItemResponseDTO;
import com.data.dto.CartResponseDTO;
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
	public CartResponseDTO getCartByUser(String email) {

	    User user = userRepo.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Cart cart = repo.findByCustomerUserId(user.getUserId())
	            .orElseThrow(() -> new RuntimeException("Cart not found"));

	    List<CartItemResponseDTO> items = cart.getCartItems().stream()
	            .map(item -> new CartItemResponseDTO(
	            		item.getCartItemId(),
	                    item.getProduct().getProductId(),
	                    item.getProduct().getProductName(),
	                    item.getProduct().getPrice(),
	                    item.getQuantity(),
	                    item.getProduct().getImageURL(),
	                    item.getQuantity() * item.getProduct().getPrice()
	            ))
	            .toList();

	    double total = items.stream()
	            .mapToDouble(CartItemResponseDTO::getSubTotal)
	            .sum();

	    return new CartResponseDTO(cart.getCartId(), items, total);
	}

	@Override
	public boolean clearCart(int customerId) {
		Optional<Cart> cart = repo.findByCustomerUserId(customerId);
		if(cart.isPresent()) {
			cart.get().getCartItems().clear();
			cart.get().setTotalAmount(0);
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

	@Override
	public Cart getCartByUser(int customerId) {
		Optional<Cart> cart=repo.findByCustomerUserId(customerId);
		
		return cart.get();
	}

	public CartResponseDTO getCartResponse(Cart cart) {

	    List<CartItemResponseDTO> items = cart.getCartItems().stream()
	        .map(item -> new CartItemResponseDTO(
	            item.getCartItemId(),
	            item.getProduct().getProductId(),
	            item.getProduct().getProductName(),
	            item.getProduct().getPrice(),
	            item.getQuantity(),
	            item.getProduct().getImageURL(),
	            item.getSubTotal()
	        ))
	        .toList();

	    return new CartResponseDTO(
	        cart.getCartId(),
	        items,
	        cart.getTotalAmount()
	    );
	}

}
