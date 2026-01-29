package com.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.exception.ResourceNotFoundException;
import com.data.model.Cart;
import com.data.model.CartItem;
import com.data.model.Product;
import com.data.repository.CartItemRepository;
import com.data.repository.CartRepository;
import com.data.repository.ProductRepository;

import lombok.Data;

@Service
@Data
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private CartItemRepository repo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CartService cartService;
//	@Override
//	public CartItem addItemToCart(int cartId, int productId, int quantity) {
//		Optional<Cart> cart=cartRepo.findById(cartId);
//		if(cart.isPresent()) {
//			Optional<CartItem> existingItem=repo.findByCartCartIdAndProductProductId(cartId, productId);
//					if (existingItem.isPresent()) {
//				        CartItem item = existingItem.get();
//				        item.setQuantity(item.getQuantity() + quantity);
//				        return repo.save(item);
//				    }
//			 Product product = productRepo.findById(productId)
//			            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//
//			    CartItem cartItem = new CartItem();
//			    cartItem.setCart(cart.get());
//			    cartItem.setProduct(product);
//			    cartItem.setQuantity(quantity);
//
//			    return repo.save(cartItem);
//		}else {
//			throw new ResourceNotFoundException("Cart not found");
//		}
//		
//	}
	@Override
    public CartItem addItemToUserCart(int userId, int productId, int quantity) {

        Cart cart = cartService.getCartByUser(userId);

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Optional<CartItem> existingItem =
                repo.findByCartCartIdAndProductProductId(
                        cart.getCartId(), productId
                );

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            return repo.save(item);
        }

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        return repo.save(item);
    }

	@Override
	public CartItem updateQuantity(int cartItemId, int quantity) {
		if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }
		CartItem item = repo.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
		
        item.setQuantity(quantity);
        
        return repo.save(item);
        		
	}

	@Override
	public boolean removeCartItem(int cartItemId) {
		 CartItem item = repo.findById(cartItemId)
	                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

	        repo.delete(item);
	        return true;
	}

	@Override
	public List<CartItem> getItemsByCart(int cartId) {
		if (!cartRepo.existsById(cartId)) {
            throw new ResourceNotFoundException("Cart not found");
        }

        return repo.findByCartCartId(cartId);
	}

	
}
