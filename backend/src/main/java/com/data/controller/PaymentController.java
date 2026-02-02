package com.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.dto.PaymentResponseDto;
import com.data.enums.PaymentMethod;
import com.data.model.Payment;
import com.data.service.PaymentService;
import com.data.service.PaymentServiceImpl;

@CrossOrigin(origins="http://localhost:5173",
allowCredentials = "true")
@RestController
@RequestMapping("api/payment")
public class PaymentController {

	@Autowired
	private PaymentServiceImpl service;
	
	@PostMapping("/initiate")
	public ResponseEntity<PaymentResponseDto> initiate(
			@RequestParam int orderId,
			@RequestParam PaymentMethod method){
	PaymentResponseDto payment=service.intiatePayment(orderId, method);
	return new ResponseEntity<> (payment,HttpStatus.OK);
	}
	
	@PutMapping("/confirm/{paymentId}")
    public ResponseEntity<Payment> confirm(@PathVariable int paymentId) {
        return ResponseEntity.ok(service.confirmPayment(paymentId, true));
    }
}
