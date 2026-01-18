package com.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUserUserId(Integer UserId);
}
