package com.data.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
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
@Table(name = "stores")
public class Store {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int storeId; //pk
	
	@NonNull
	private String storeName;
	@NonNull
	private String storeLogo; //url
	@NonNull
	private String storeDiscription;
	@CreatedDate
	private LocalDateTime createdAT;
	
	@ManyToOne
	@JoinColumn(name="vendorId") //FK
	private User vendor;

	@OneToMany(mappedBy="store",cascade=CascadeType.ALL)
	private List<Product> products=new ArrayList<>();

}
