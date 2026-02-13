package com.data.dto;

import com.data.enums.OrderStatus;
import com.data.enums.PaymentMethod;
import com.data.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponseDto {

	private int paymentId;
    private double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String transactionId;

    private int orderId;
    private OrderStatus orderStatus;
}
