package com.hrs.payment.kafka;

import com.hrs.dto.ReservationReqDto;
import com.hrs.payment.model.Payment;
import com.hrs.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ConsumerService {

    @Autowired
    private PaymentRepository paymentRepository;

    @KafkaListener(topics = "reservation-created",
            groupId = "reservation-group")
    public void consumeReservationMessage(ReservationReqDto reqDto) {
        log.info("Message consumed waiting for the payment {}", reqDto);

        var payment = new Payment();
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setReservationId(reqDto.getReservationId());
        payment.setCustomerId(reqDto.getCustomerId());
        payment.setHotelId(reqDto.getHotelId());
        payment.setAmount(reqDto.getAmount());
        payment.setStatus("Waiting For Payment");
        paymentRepository.save(payment);

    }
}
