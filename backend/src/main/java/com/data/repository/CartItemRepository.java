package com.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	 List<CartItem> findByCartCartId(Integer cartId);
}
