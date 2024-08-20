package com.hrs.payment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="payment")
public class Payment {

	@Id
	@Column(name="payment_id")
	private String paymentId;

	@Column(name="reservation_id")
	private String reservationId;

	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="hotel_id")
	private Long hotelId;

	@Column(name="amount")
	private Integer amount;

	@Column(name="status")
	private String status;


}