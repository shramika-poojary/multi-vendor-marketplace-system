package com.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId; //pk
	
	private int flatNo;
	
	@NonNull
	private String aptName;
	@NonNull
	private String street;
	@NonNull
	private String city;
	@NonNull
	private String state;
	@NonNull
	private String pincode;
	@NonNull
	private String country;

	@ManyToOne
	@JoinColumn(name="userId") 
	private User user; //fk

}
