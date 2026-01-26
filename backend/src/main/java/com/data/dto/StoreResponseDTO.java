package com.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDTO {
	private int storeId;
    private String storeName;
    private String storeLogo;
    private String storeDiscription;
}
