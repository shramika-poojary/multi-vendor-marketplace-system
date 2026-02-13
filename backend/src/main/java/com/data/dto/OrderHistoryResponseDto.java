package com.data.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class OrderHistoryResponseDto {

	private int orderId;
	private double total;
	private LocalDateTime orderDate;
	
	private List<OrderItemDto> items;
	
}
