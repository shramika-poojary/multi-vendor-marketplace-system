package com.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.dto.CartRequestDTO;
import com.data.model.Cart;
import com.data.model.CartItem;
import com.data.model.User;
import com.data.service.CartItemServiceImpl;
import com.data.service.CartServiceImpl;
import com.data.service.UserServiceImpl;
@CrossOrigin(origins="http://localhost:5173",
allowCredentials = "true")
@RestController
@PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("api/cart")
public class CartController {

	@Autowired
	private CartServiceImpl cartService;
	
	@Autowired
	private CartItemServiceImpl cartItemService;
	
	@Autowired
	private UserServiceImpl UserService;
//	// Create cart for logged-in customer
//	@PostMapping("create/cart/{customerId}")
//	public ResponseEntity<?> create_cart(@PathVariable int customerId){
//		Cart cart=cartService.createCart(customerId);
//		
//		return new ResponseEntity<>(cart,HttpStatus.CREATED);
//	}
//	
//	// Get logged-in customer's cart
//	@GetMapping("get/cart/{userId}")
//	public ResponseEntity<?> get_cart(@PathVariable int userId){
//		Cart cart=cartService.getCartByUser(userId);
//		
//		return new ResponseEntity<>(cart,HttpStatus.OK);
//	}
//	
//	
////	// Clear cart
////    @DeleteMapping
////    public ResponseEntity<Boolean> clearCart() {
////        int customerId = authService.getLoggedInUserId();
////        return ResponseEntity.ok(cartService.clearCart(customerId));
////    }
//	
//	@DeleteMapping("clear/cartitem/{customerId}")
//	public ResponseEntity<?> clear_cart(@PathVariable int customerId){
//		boolean cart=cartService.clearCart(customerId);
//		
//		return new ResponseEntity<>(cart,HttpStatus.OK);
//	}
//	
//	//something wrong here
//	@PostMapping("addtocart/{cartId}/{productId}/{quantity}")
//	public ResponseEntity<?> add_item_to_cart(@PathVariable int cartId, @PathVariable int productId,@PathVariable int quantity){
//		CartItem cartItem=cartItemService.addItemToCart(cartId, productId, quantity);
//				
//			return new ResponseEntity<>(cartItem,HttpStatus.OK);
//	}
//	
//	@PutMapping("/items/{cartItemId}")
//	public ResponseEntity<?> update_cart_item(@PathVariable int cartItemId,@RequestParam int quantity){
//		CartItem item=cartItemService.updateQuantity(cartItemId, quantity);
//		return new ResponseEntity<>(item,HttpStatus.OK);
//	}
//	
//	//remove cart item 
//	 // Remove cart item
//    @DeleteMapping("/items/{cartItemId}")
//    public ResponseEntity<Boolean> removeItem(@PathVariable int cartItemId) {
//        return ResponseEntity.ok(cartItemService.removeCartItem(cartItemId));
//    }
//    
//    //somethig wromg here
//	@GetMapping("get/cartitems/{cartId}")
//	public ResponseEntity<?> get_cart_items(@PathVariable int cartId){
//		List<CartItem> cartItems=cartItemService.getItemsByCart(cartId);
//		return new ResponseEntity<>(cartItems,HttpStatus.OK);
//	}
	
	// 1️⃣ Get my cart
    @GetMapping
    public ResponseEntity<Cart> getMyCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
                cartService.getCartByUser(user.getUserId())
        );
    }

    // 2️⃣ Add item to cart
    @PostMapping("/items")
    public ResponseEntity<CartItem> addItem(
            @AuthenticationPrincipal UserDetails user,
            @RequestBody CartRequestDTO request
    ) {
    	String email=user.getUsername();
    	User data=UserService.getUserByEmail(email);
    	Cart create=cartService.createCart(data.getUserId());
        CartItem item = cartItemService.addItemToUserCart(
                data.getUserId(), request.getProductId(), request.getQuantity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    // 3️⃣ Update quantity
    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartItem> updateItem(
            @PathVariable int cartItemId,
            @RequestParam int quantity
    ) {
        return ResponseEntity.ok(
                cartItemService.updateQuantity(cartItemId, quantity)
        );
    }

    // 4️⃣ Remove item
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<?> removeItem(@PathVariable int cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return ResponseEntity.ok("Item removed");
    }

    // 5️⃣ Clear cart
    @DeleteMapping
    public ResponseEntity<?> clearCart(@AuthenticationPrincipal User user) {
        cartService.clearCart(user.getUserId());
        return ResponseEntity.ok("Cart cleared");
    }

}
