package com.data.service;

import java.util.List;

import com.data.model.OrderItem;

public interface OrderItemService {

	List<OrderItem> getItemsByOrder(int orderId);
	 List<OrderItem> createOrderItemsFromCart(int orderId, int cartId);
	 boolean deleteItemsByOrder(int orderId);
}
