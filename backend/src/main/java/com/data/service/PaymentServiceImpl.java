package com.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dto.PaymentResponseDto;
import com.data.enums.OrderStatus;
import com.data.enums.PaymentMethod;
import com.data.enums.PaymentStatus;
import com.data.exception.ResourceNotFoundException;
import com.data.model.Order;
import com.data.model.Payment;
import com.data.repository.OrderRepository;
import com.data.repository.PaymentRepository;
import com.razorpay.RazorpayException;

import lombok.Data;

@Service
@Data
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private CartServiceImpl cartService;
	
	@Override
	public PaymentResponseDto intiatePayment(int orderId ,PaymentMethod method){
		  Order order = orderRepo.findById(orderId)
		            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

		Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotal());
        payment.setPaymentMethod(method);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setTransactionId("TXN-" + System.currentTimeMillis());

        Payment savedPayment = paymentRepo.save(payment);

        order.setOrderStatus(OrderStatus.PENDING);
        orderRepo.save(order);

        return new PaymentResponseDto(
        		savedPayment.getPaymentId(),
        		savedPayment.getAmount(),
        		savedPayment.getPaymentMethod(),
        		savedPayment.getPaymentStatus(),
        		savedPayment.getTransactionId(),
        		order.getOrderId(),
        		order.getOrderStatus()
        		
        		);
	}

	@Override
	public Payment confirmPayment(int paymentId, boolean success) {
		Payment payment=paymentRepo.findById(paymentId)
				.orElseThrow(()->new ResourceNotFoundException("Payment not found"));
		if(success) {
			payment.setPaymentStatus(PaymentStatus.SUCCESS);
			payment.getOrder().setOrderStatus(OrderStatus.PLACED);
			int customerId = payment.getOrder().getCustomer().getUserId();
	        cartService.clearCart(customerId);
			
		}else {
			payment.setPaymentStatus(PaymentStatus.FAILED);
			payment.getOrder().setOrderStatus(OrderStatus.CANCELLED);
		}
		
		orderRepo.save(payment.getOrder());
		return  paymentRepo.save(payment);
	}
	
	
}
