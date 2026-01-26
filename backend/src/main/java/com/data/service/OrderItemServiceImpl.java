package com.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.exception.ResourceNotFoundException;
import com.data.model.Cart;
import com.data.model.Order;
import com.data.model.OrderItem;
import com.data.repository.CartRepository;
import com.data.repository.OrderItemRepository;
import com.data.repository.OrderRepository;

import lombok.Data;

@Service
@Data
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
    private OrderItemRepository repo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CartRepository cartRepo;
    
	@Override
	public List<OrderItem> getItemsByOrder(int orderId) {
		
		 if (!orderRepo.existsById(orderId)) {
	            throw new ResourceNotFoundException("Order not found");
	        }

	        return repo.findByOrderOrderId(orderId);
	}

	@Override
	public List<OrderItem> createOrderItemsFromCart(int orderId, int cartId) {
		 Order order = orderRepo.findById(orderId)
	                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

	        Cart cart = cartRepo.findById(cartId)
	                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

	        if (cart.getCartItems().isEmpty()) {
	            throw new IllegalStateException("Cart is empty");
	        }
	        
	        List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {

	            OrderItem orderItem = new OrderItem();
	            orderItem.setOrder(order);
	            orderItem.setProduct(cartItem.getProduct());
	            orderItem.setQuantity(cartItem.getQuantity());
	            orderItem.setPrice(cartItem.getProduct().getPrice());

	            return orderItem;
	        }).toList();

	        return repo.saveAll(orderItems);
	        
	}

	@Override
	public boolean deleteItemsByOrder(int orderId) {
		 if (!orderRepo.existsById(orderId)) {
	            throw new ResourceNotFoundException("Order not found");
	        }

	        repo.deleteByOrderOrderId(orderId);
	        return true;
	}

}
