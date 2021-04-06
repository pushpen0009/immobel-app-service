package com.immobel.service;

import java.util.List;

import com.immobel.model.Invoice;

public interface InvoiceService {

    Invoice create(Invoice invoice);

    Invoice update(Invoice invoice);

    Invoice get(Integer invoiceId);

    List<Invoice> findAll();

    List<Invoice> findAllInvoicesPendingForReminder();

	void delete(Integer invoiceId);
}
