package com.data.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.data.enums.PaymentMethod;
import com.data.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId; //pk
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	@NonNull
	private double amount;
	@NonNull
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	@NonNull
	private String transactionId;
	@CreatedDate
    private LocalDateTime paymentDate;

	@ManyToOne
	@JoinColumn(name="orderId") //fk
	private Order order;

}
