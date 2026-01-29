package com.data.service;

import java.util.List;

import com.data.dto.ProductResponseDTO;
import com.data.model.Product;

public interface ProductService {

	Product addProduct(Product product, int storeId);
	Product getProductById(int productId);
	List<ProductResponseDTO> getAllProductsByStoreId(int storeId);
	Product updateProduct(Product product,int productId);
	boolean deleteProduct(int productId);
}
