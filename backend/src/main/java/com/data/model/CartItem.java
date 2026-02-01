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
@Table(name = "cart_item")
public class CartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartItemId; //pk
	
	@NonNull
	private int quantity;
	@NonNull
	private float price;
	@NonNull
    private float subTotal;
	@ManyToOne
	@JoinColumn(name="cartId") //fk
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name="productId") //fk
	private Product product;

}
