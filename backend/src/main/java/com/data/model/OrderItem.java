package com.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "order_item")
public class OrderItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderIitemIid;
	
	@NonNull
	private int quantity;
	@NonNull
	private double price;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;


}
