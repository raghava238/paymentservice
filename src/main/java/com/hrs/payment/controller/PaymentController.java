package com.hrs.payment.controller;

import com.hrs.dto.PaymentDto;
import com.hrs.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@Slf4j
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/updatePaymentStatus")
	public ResponseEntity<?> updatePaymentStaus(@RequestBody PaymentDto paymentDto) {
		log.info("payment confirmation req Received");
		paymentService.updatePaymentStatus(paymentDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/reservation/{reservation-id}")
	public PaymentDto getPaymentByReservationId(@PathVariable("reservation-id") String reservationId) {
		return paymentService.getPaymentByReservationId(reservationId);
	}


}