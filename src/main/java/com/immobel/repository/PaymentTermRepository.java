package com.immobel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.immobel.model.PaymentTerm;

@Repository
public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Integer> {

    PaymentTerm findPaymentTermByCode(String code);
}
