package com.data.service;

import java.util.List;

import com.data.dto.CartResponseDTO;
import com.data.dto.UpdateCartItemRequestDTO;
import com.data.model.Cart;
import com.data.model.CartItem;

public interface CartItemService {

//	CartItem addItemToCart(int cartId,int productId, int quantity);
	//CartItem updateQuantity(int cartItemId,int quantity);
	CartResponseDTO removeCartItem(int cartItemId);
	List<CartItem> getItemsByCart(int cartId);
	CartItem addItemToUserCart(int userId, int productId, int quantity);
	CartResponseDTO updateQuantity(int cartItemId, UpdateCartItemRequestDTO request);
}
