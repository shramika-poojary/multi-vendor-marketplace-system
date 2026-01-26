package com.data.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.enums.OrderStatus;
import com.data.exception.ResourceNotFoundException;
import com.data.model.Cart;
import com.data.model.Order;
import com.data.model.User;
import com.data.repository.CartRepository;
import com.data.repository.OrderRepository;
import com.data.repository.UserRepository;

import lombok.Data;

@Service
@Data
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private UserRepository userRepo;
	    
	@Override
	public Order placeOrder(int customerId) {
		
		 User customer = userRepo.findById(customerId)
	                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

	        Cart cart = cartRepo.findByCustomerUserId(customerId)
	                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

	        if (cart.getCartItems().isEmpty()) {
	            throw new IllegalStateException("Cart is empty");
	        }
	        
	        double total = cart.getCartItems().stream()
	                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
	                .sum();
	        Order order = new Order();
	        order.setCustomer(customer);
	        order.setOrderStatus(OrderStatus.PLACED);
	        order.setOrderDate(LocalDateTime.now());
	        order.setTotal(total);
	        
	        Order savedOrder = repo.save(order);

	        cart.getCartItems().clear();
	        cartRepo.save(cart);

	        return savedOrder;
	}

	@Override
	public Order getOrderById(int orderId) {
		Optional<Order> order=repo.findById(orderId);
		if(order.isPresent()) {
			return order.get();
		}else {
			throw new ResourceNotFoundException("Ivalid OrderId");
		}
	}

	@Override
	public List<Order> getOrdersByUser(int cutomerId) {
		List<Order> orders=repo.findByCustomerUserId(cutomerId);
		if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders found for this user");
        }
		return orders;
	}

	@Override
	public boolean updateOrderStatus(int orderId, String status) {
		Order order = repo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
        repo.save(order);

        return true;
	}

	@Override
	public Order cancelOrder(int orderId) {
		Order order = repo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getOrderStatus() == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Delivered order cannot be cancelled");
        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        return repo.save(order);
    }
	
}
