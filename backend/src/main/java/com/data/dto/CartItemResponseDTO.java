package com.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemResponseDTO {
	private int cartItemId;
	private int productId;
    private String productName;
    private float price;
    private int quantity;
    private String imageUrl;
    private double subTotal;
}
