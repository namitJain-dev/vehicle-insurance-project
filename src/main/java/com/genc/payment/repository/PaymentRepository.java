package com.genc.payment.repository;

import com.genc.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // JpaRepository provides save(), findById(), etc. automatically!
}