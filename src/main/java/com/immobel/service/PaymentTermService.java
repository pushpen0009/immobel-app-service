package com.immobel.service;

import java.util.List;

import com.immobel.model.PaymentTerm;

public interface PaymentTermService {

    PaymentTerm create(PaymentTerm paymentTerm);

    PaymentTerm update(PaymentTerm paymentTerm);

    PaymentTerm get(int paymentTermId);

    void delete(int paymentTermId);

    List<PaymentTerm> findAll();
}
