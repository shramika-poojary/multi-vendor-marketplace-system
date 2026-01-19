package com.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByOrderOrderId(Integer orderId);
	
	boolean deleteByOrderOrderId(int orderId);
}
