package com.data.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.data.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name ="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId; //pk
	
	@NonNull
	@NotBlank(message="This field is required")
	private String name;

	@NonNull
	@NotBlank(message = "this field is required")
	@Email(regexp = "^\\S+@\\S+\\.\\S+$",message = "please enter valid email")
	@Column(nullable=false,unique=true)
	private String email;
	
	@NonNull
	@NotBlank(message = "this field is required")
	private String password;

	@NonNull
	@NotBlank(message = "please enter a contact number")
	@Pattern(regexp = "^\\d{10}$", message = "Please enter a valid 10-digit contact number")
	@Column(nullable=false,unique=true)
	private String contact;

	@NonNull
	@Enumerated(EnumType.STRING)
	private Role role;  //vendor or customer or admin
	
	@CreatedDate
	private LocalDateTime createdAt;

	@OneToMany(mappedBy ="user" ,cascade=CascadeType.ALL) 
	private List<Address> addresses=new ArrayList<>();
	
	@OneToMany(mappedBy = "vendor",cascade=CascadeType.ALL)
	private List<Store> stores=new ArrayList<>();
	
	@OneToOne(mappedBy="customer",cascade=CascadeType.ALL)
	private Cart cart;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();

}
