package com.immobel.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.immobel.model.Invoice;
import com.immobel.model.PaymentTerm;
import com.immobel.repository.InvoiceRepository;
import com.immobel.repository.PaymentTermRepository;
import com.immobel.service.InvoiceService;
import com.immobel.utils.CommonUtils;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private PaymentTermRepository paymentTermRepository;

	@Override
	public Invoice create(Invoice invoice) {
		if(CommonUtils.isBlank(invoice.getPaymentTerm())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment term can not be blank");
		}
		PaymentTerm paymentTerm = this.paymentTermRepository.findPaymentTermByCode(invoice.getPaymentTerm());
		if(Objects.isNull(paymentTerm)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment term");
		}
		invoice.setInvoiceDate(new Date());
		return this.invoiceRepository.save(invoice);
	}

	@Override
	public Invoice update(Invoice invoice) {
		if(!Optional.ofNullable(invoice.getInvoiceId()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invoive ID can not be blank");
		}
		if(CommonUtils.isBlank(invoice.getPaymentTerm())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment term can not be blank");
		}
		PaymentTerm paymentTerm = this.paymentTermRepository.findPaymentTermByCode(invoice.getPaymentTerm());
		if(Objects.isNull(paymentTerm)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment term");
		}
		
		if(!this.invoiceRepository.findById(invoice.getInvoiceId()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invoive ID is not valid");
		}
		return this.invoiceRepository.save(invoice);
	}

	@Override
	public Invoice get(Integer invoiceId) {
		return invoiceRepository.findById(invoiceId).orElse(null);
	}

	@Override
	public void delete(Integer invoiceId) {
		Invoice invoice = get(invoiceId);
		if(Objects.isNull(invoice)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid invoice id");
		}
		invoiceRepository.deleteById(invoiceId);
	}

	@Override
	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public List<Invoice> findAllInvoicesPendingForReminder() {
		return this.invoiceRepository.findAllInvoicesPendingForReminder();
	}

}
