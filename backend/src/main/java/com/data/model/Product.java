package com.data.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId; //pk
	@NonNull
	private String productName;
	@NonNull
	private String productDescription;
	@NonNull
	private Float price;
	@NonNull
	private String imageURL;
	@NonNull
	private String stock;
	@CreatedDate
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "storeId") //fk
	private Store store;

	@OneToMany(mappedBy="product")
	private List<CartItem> cartItems;
	
	@OneToMany(mappedBy="product")
	private List<OrderItem> orderItems;

}
