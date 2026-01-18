package com.data.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data

public class Cart {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId; //pk
	
	
	
	public Cart(int cartId, User customer, List<CartItem> cartItems) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.cartItems = cartItems;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToOne
	@JoinColumn(name="customerId") //fk
	private User customer;
	
	@OneToMany(mappedBy="cart")
	private List<CartItem> cartItems=new ArrayList<>();

}
