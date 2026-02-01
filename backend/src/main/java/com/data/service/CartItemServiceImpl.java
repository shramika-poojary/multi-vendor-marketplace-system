package com.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.dto.CartResponseDTO;
import com.data.dto.UpdateCartItemRequestDTO;
import com.data.exception.ResourceNotFoundException;
import com.data.model.Cart;
import com.data.model.CartItem;
import com.data.model.Product;
import com.data.repository.CartItemRepository;
import com.data.repository.CartRepository;
import com.data.repository.ProductRepository;

import jakarta.transaction.Transactional;
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
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPrice(product.getPrice());
        item.setSubTotal(product.getPrice() * quantity);
        item.setCart(cart);
        return repo.save(item);
    }

	@Override
	@Transactional
	public CartResponseDTO updateQuantity(
	        int cartItemId,
	        UpdateCartItemRequestDTO request
	) {
	    CartItem item = repo.findById(cartItemId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

	    Cart cart = item.getCart();

	    if (request.getQuantity() <= 0) {
	        repo.delete(item);
	    } else {
	        item.setQuantity(request.getQuantity());
	        item.setSubTotal(item.getPrice() * request.getQuantity());
	        repo.save(item);
	    }

	    recalculateCart(cart);

	    return cartService.getCartResponse(cart); // ✅ FULL CART
	}


	@Override
	@Transactional
	public CartResponseDTO removeCartItem(int cartItemId) {

	    CartItem item = repo.findById(cartItemId)
	            .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

	    Cart cart = item.getCart();

	    repo.delete(item);
	    recalculateCart(cart);

	    return cartService.getCartResponse(cart); // ✅ FULL CART
	}

	private void recalculateCart(Cart cart) {
	    Double total = repo.findByCartCartId(cart.getCartId())
	            .stream()
	            .mapToDouble(CartItem::getSubTotal)
	            .sum();

	    cart.setTotalAmount(total);
	    cartRepo.save(cart);
	}


	
	@Override
	public List<CartItem> getItemsByCart(int cartId) {
		if (!cartRepo.existsById(cartId)) {
            throw new ResourceNotFoundException("Cart not found");
        }

        return repo.findByCartCartId(cartId);
	}

	
}
