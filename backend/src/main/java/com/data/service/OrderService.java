package com.data.service;

import java.util.List;

import com.data.dto.OrderHistoryResponseDto;
import com.data.dto.OrderResponseDto;
import com.data.model.Order;

public interface OrderService {

	OrderResponseDto placeOrder(int customerId);
	Order getOrderById(int orderId);
	List<OrderHistoryResponseDto> getOrdersByUser(int cutomerId);
	boolean updateOrderStatus(int orderId,String status);
	Order cancelOrder(int orderId);
	
}
