package com.hrs.payment.service;

import com.hrs.dto.PaymentDto;
import com.hrs.payment.exception.ValidationException;
import com.hrs.payment.kafka.ProducerService;
import com.hrs.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProducerService producerService;


	public void updatePaymentStatus(PaymentDto paymentDto) {

		//Fetch the HotelInformation
		var paymentOptional = paymentRepository.findByPaymentIdAndStatus(paymentDto.getPaymentId(), "Waiting For Payment");

		if (paymentOptional.isEmpty()) {
			throw new ValidationException("No Pending Payment Req Found");
		}
		var payment = paymentOptional.get();
		payment.setStatus("Completed");
		payment.setPaymentDate(LocalDate.now());
		payment = paymentRepository.save(payment);

		producerService.publishPaymentCompletedEvent(modelMapper.map(payment, PaymentDto.class));
		log.info("Payment Confirmation Received");

	}

	public PaymentDto getPaymentByReservationId(String reservationId) {
		return paymentRepository.findByReservationId(reservationId)
				.map(payment -> modelMapper.map(payment, PaymentDto.class))
				.orElse(null);
	}
}