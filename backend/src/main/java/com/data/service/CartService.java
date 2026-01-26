package com.data.service;

import com.data.model.Cart;

public interface CartService {

	Cart  createCart(int customerId);
	Cart getCartByUser(int customerId);
	boolean clearCart(int customerId);// after order placed
	int  getCartId(int customerId);
	
	
}
