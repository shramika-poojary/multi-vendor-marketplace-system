package com.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dto.ProductResponseDTO;
import com.data.exception.ResourceNotFoundException;
import com.data.model.Product;
import com.data.model.Store;
import com.data.repository.ProductRepository;
import com.data.repository.StoreRepository;

import lombok.Data;

@Service
@Data
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private StoreRepository storeRepo;
	
	@Override
	public Product addProduct(Product product, int storeId) {
		Optional<Store> store=storeRepo.findById(storeId);
		if(store.isPresent()) {
			product.setStore(store.get());
			return repo.save(product);
		}else {
			throw new ResourceNotFoundException("Store Not Found");
			
		}
	}

	@Override
	public Product getProductById(int productId) {
		Optional<Product> product=repo.findById(productId);
		if(product.isPresent()) {
			return product.get();
		}else {
			throw new ResourceNotFoundException("Product Not Found");
		}
	}

	@Override
	public List<ProductResponseDTO> getAllProductsByStoreId(int storeId) {
		List<ProductResponseDTO> product=repo.findByStoreStoreId(storeId).stream()
				.map(p->new ProductResponseDTO(
						p.getProductId(),
	                    p.getProductName(),
	                    p.getProductDescription(),
	                    p.getPrice(),
	                    p.getImageURL(),
	                    p.getStock()
						
						)).toList();
		
		return product;
		
		
	}

	@Override
	public Product updateProduct(Product product, int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
