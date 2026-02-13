package com.data.dto;

import java.time.LocalDateTime;

import com.data.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

	private int orderId;
    private double total;
    private OrderStatus orderStatus;
    private String paymentGatewayId;
    
}
