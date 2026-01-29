package com.data.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.data.enums.OrderStatus;
import com.data.enums.PaymentStatus;
import com.data.exception.ResourceNotFoundException;
import com.data.model.Order;
import com.data.model.Payment;
import com.data.repository.OrderRepository;
import com.data.repository.PaymentRepository;

public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public Payment makePayment(int orderId,Payment method) {
		  Order order = orderRepo.findById(orderId)
		            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

		Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotal());
        payment.setPaymentMethod(method.getPaymentMethod());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId("TXN-" + System.currentTimeMillis());

        Payment savedPayment = paymentRepo.save(payment);

        order.setOrderStatus(OrderStatus.PLACED);
        orderRepo.save(order);

        return savedPayment;
	}
}
