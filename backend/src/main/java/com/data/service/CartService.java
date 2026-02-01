package com.data.service;

import com.data.dto.CartResponseDTO;
import com.data.model.Cart;

public interface CartService {

	Cart  createCart(int customerId);
	Cart getCartByUser(int customerId);
	boolean clearCart(int customerId);// after order placed
	int  getCartId(int customerId);
	CartResponseDTO getCartByUser(String email);
	CartResponseDTO getCartResponse(Cart cart);
	
	
}
