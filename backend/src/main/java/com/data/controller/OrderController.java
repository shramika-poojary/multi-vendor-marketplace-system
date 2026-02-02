package com.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.dto.OrderHistoryResponseDto;
import com.data.dto.OrderResponseDto;
import com.data.model.Order;
import com.data.model.OrderItem;
import com.data.model.Store;
import com.data.model.User;
import com.data.service.OrderItemServiceImpl;
import com.data.service.OrderServiceImpl;
import com.data.service.UserServiceImpl;
@CrossOrigin(origins="http://localhost:5173",
allowCredentials = "true")
@RestController
@PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("api/orders")

public class OrderController {

	@Autowired
	private OrderServiceImpl service;
	
	@Autowired
	private OrderItemServiceImpl itemsService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("place/order")
	ResponseEntity<?> place_order(@AuthenticationPrincipal UserDetails userDetails){
		String email=userDetails.getUsername();
		User user=userService.getUserByEmail(email);
		OrderResponseDto order=service.placeOrder(user.getUserId());
		
		return new ResponseEntity(order,HttpStatus.CREATED);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<?> get_order_by_id(@PathVariable int orderId){
		Order order=service.getOrderById(orderId);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@GetMapping("get/orders")
	public ResponseEntity<?> get_orders_by_user(@AuthenticationPrincipal UserDetails userDetails){
		String email=userDetails.getUsername();
		User user=userService.getUserByEmail(email);
		List<OrderHistoryResponseDto> order=service.getOrdersByUser(user.getUserId());
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@GetMapping("/order/items/{orderId}")
	public ResponseEntity<?> get_orders_items_by_id(@PathVariable int orderId){
		List<OrderItem> orderItems=itemsService.getItemsByOrder(orderId);
		return new ResponseEntity<>(orderItems,HttpStatus.OK);
	}
	
	//cancel order
	
}
