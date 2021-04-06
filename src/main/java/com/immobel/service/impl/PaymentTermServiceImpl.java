package com.immobel.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.immobel.model.PaymentTerm;
import com.immobel.repository.PaymentTermRepository;
import com.immobel.service.PaymentTermService;

@Service
public class PaymentTermServiceImpl implements PaymentTermService {

    @Autowired
    private PaymentTermRepository paymentTermRepository;

    @Override
    public PaymentTerm create(PaymentTerm paymentTerm) {
        paymentTerm.setCreationDate(new Date());
        return this.paymentTermRepository.save(paymentTerm);
    }

    @Override
    public PaymentTerm update(PaymentTerm paymentTerm) {
		if(!Optional.ofNullable(paymentTerm.getPaymentTermId()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment term ID can not be blank");
		}
		
		if(!this.paymentTermRepository.findById(paymentTerm.getPaymentTermId()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment term ID is not valid");
		}
        return this.paymentTermRepository.save(paymentTerm);
    }

    @Override
    public PaymentTerm get(int paymentTermId) {
        return this.paymentTermRepository.findById(paymentTermId).orElse(null);
    }

    @Override
    public void delete(int paymentTermId) {
        PaymentTerm paymentTerm = get(paymentTermId);
        if(Objects.isNull(paymentTerm)){
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment term ID is not valid");
        }
        this.paymentTermRepository.deleteById(paymentTermId);
    }

    @Override
    public List<PaymentTerm> findAll() {
        return this.paymentTermRepository.findAll();
    }

}
