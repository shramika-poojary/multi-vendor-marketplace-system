package com.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {
	private int productId;
	private String productName;
	private String productDescription;
	private Float price;
	private String imageURL;
	private String stock;
}
