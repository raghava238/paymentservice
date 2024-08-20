package com.hrs.payment.kafka;

import com.hrs.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPaymentCompletedEvent(PaymentDto paymentDto) {
        log.info("publish Payment Completion Event {}", paymentDto);
        this.kafkaTemplate.send("payment-completed", paymentDto);
        log.info("Message Published");
    }
}
