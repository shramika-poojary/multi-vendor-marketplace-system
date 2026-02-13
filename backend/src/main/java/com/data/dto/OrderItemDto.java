package com.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
public class OrderItemDto {
	private float price;
	private int quantity;
	private String productName;
	private String imageURL;
}
