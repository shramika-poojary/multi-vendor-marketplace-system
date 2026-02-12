package com.data.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.data.dto.ProductResponseDTO;
import com.data.model.Product;
import com.data.service.ProductServiceImpl;

import io.jsonwebtoken.io.IOException;
@CrossOrigin(origins="http://localhost:5173",
				allowCredentials = "true")
@RestController
@RequestMapping("api/product")
public class ProductController {

	
	@Autowired
	private ProductServiceImpl productService;
	
	@PreAuthorize("hasRole('VENDOR')")
	@PostMapping("add/products/{storeId}")
	public ResponseEntity<?> add_product(@RequestBody Product product, @PathVariable  int storeId){
		Product item=productService.addProduct(product, storeId);
		return new ResponseEntity(item,HttpStatus.CREATED);
	}
	
	@GetMapping("get/products/{productId}")
	public ResponseEntity<?> get_product_by_id(@PathVariable int productId){
		Product product=productService.getProductById(productId);
		 return new ResponseEntity<>(product,HttpStatus.OK);
	}

	@GetMapping("store/{storeId}")
	public ResponseEntity<?> get_products_by_store(@PathVariable int storeId){
		List<ProductResponseDTO> products=productService.getAllProductsByStoreId(storeId);
			return new ResponseEntity<>(products,HttpStatus.OK);
	}

	//Image upload
	
			@PostMapping("/upload")
		    public String uploadImage(@RequestParam("file") MultipartFile file)
		            throws IOException, IllegalStateException, java.io.IOException {
					String fileName = file.getOriginalFilename();
					String path = "C:\\multi-vendor-marketplace-system\\backend\\uploads\\stores\\" + fileName;

					file.transferTo(new File(path));

					return "/images/" + file.getOriginalFilename();
				

		    }
	
}
