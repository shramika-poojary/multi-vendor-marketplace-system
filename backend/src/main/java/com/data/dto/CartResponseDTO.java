package com.data.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartResponseDTO {

	  private int cartId;
	  private List<CartItemResponseDTO> items;
	  private double totalAmount;
}
