package com.data.service;

import java.util.List;

import com.data.model.Order;

public interface OrderService {

	Order placeOrder(int customerId);
	Order getOrderById(int orderId);
	List<Order> getOrdersByUser(int cutomerId);
	boolean updateOrderStatus(int orderId,String status);
	Order cancelOrder(int orderId);
	
}
