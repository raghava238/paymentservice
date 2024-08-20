package com.hrs.payment.repository;

import com.hrs.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {


    Optional<Payment> findByPaymentIdAndStatus(String paymentId, String status);

    Optional<Payment> findByReservationId(String reservationId);
}