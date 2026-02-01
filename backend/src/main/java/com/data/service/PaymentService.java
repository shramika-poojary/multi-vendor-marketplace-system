package com.data.service;

import com.data.dto.PaymentResponseDto;
import com.data.enums.PaymentMethod;
import com.data.model.Payment;
import com.razorpay.RazorpayException;

public interface PaymentService {

	

	PaymentResponseDto intiatePayment(int orderId, PaymentMethod method);
	Payment confirmPayment(int paymentId,boolean success);
}
