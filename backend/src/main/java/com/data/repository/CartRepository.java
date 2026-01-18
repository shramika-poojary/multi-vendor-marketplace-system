package com.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUserUserId(Integer userId);
}
