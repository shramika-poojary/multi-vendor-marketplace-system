package com.data.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.data.enums.OrderStatus;
import com.data.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId; //pk
	
	@NonNull
	private double total;
	@NonNull
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@NonNull
	private String paymentGatewayId;

	@CreatedDate
	private LocalDateTime orderDate;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name="customer_id") //fk
	private User customer;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	private List<OrderItem> orderItems= new ArrayList<>();
	

}
