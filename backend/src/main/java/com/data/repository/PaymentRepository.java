package com.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Optional<Payment> findByOrderOrderId(Integer orderId);
}
