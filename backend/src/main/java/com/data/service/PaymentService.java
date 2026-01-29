package com.data.service;

import com.data.model.Payment;

public interface PaymentService {

	Payment makePayment(int orderId, Payment method);

}
