package com.data.service;

import java.util.List;

import com.data.model.CartItem;

public interface CartItemService {

	CartItem addItemToCart(int cartId,int productId, int quantity);
	CartItem updateCartItem(int cartItemId,int quantity);
	boolean removeCartItem(int cartItemId);
	List<CartItem> getItemsByCart(int cartId);
}
