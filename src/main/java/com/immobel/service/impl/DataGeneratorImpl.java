package com.immobel.service.impl;

import java.util.Calendar;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immobel.model.Invoice;
import com.immobel.model.PaymentTerm;
import com.immobel.model.Status;
import com.immobel.service.DataGenerator;
import com.immobel.service.InvoiceService;
import com.immobel.service.PaymentTermService;
import com.immobel.utils.ImmobelConstants;

@Service
public class DataGeneratorImpl implements DataGenerator {

    @Autowired
    private PaymentTermService paymentTermService;

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public void generateInitialData() {
        this.createPaymentTerms();
        this.createInvoices();
        this.updateDateForOneReminder();
    }

    private void updateDateForOneReminder(){
        Invoice invoice = this.invoiceService.get(1);
        PaymentTerm paymentTerm = this.paymentTermService.get(1);
        if(Objects.nonNull(invoice) && Objects.nonNull(paymentTerm)){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -(paymentTerm.getDays()-paymentTerm.getRemindBeforeDays()));
            invoice.setInvoiceDate(calendar.getTime());
            this.invoiceService.update(invoice);
        }
    }

    private void createPaymentTerms(){
        PaymentTerm paymentTerm1 = new PaymentTerm();
        paymentTerm1.setDays(30);
        paymentTerm1.setCode(ImmobelConstants.NET_30);
        paymentTerm1.setRemindBeforeDays(4);
        paymentTerm1.setDescription(ImmobelConstants.Within_30_days);
        this.paymentTermService.create(paymentTerm1);

        PaymentTerm paymentTerm2 = new PaymentTerm();
        paymentTerm2.setDays(45);
        paymentTerm2.setCode(ImmobelConstants.NET_45);
        paymentTerm2.setRemindBeforeDays(5);
        paymentTerm2.setDescription(ImmobelConstants.Within_45_days);
        this.paymentTermService.create(paymentTerm2);

        PaymentTerm paymentTerm3 = new PaymentTerm();
        paymentTerm3.setDays(15);
        paymentTerm3.setCode(ImmobelConstants.NET_15);
        paymentTerm3.setRemindBeforeDays(2);
        paymentTerm3.setDescription(ImmobelConstants.Within_15_days);
        this.paymentTermService.create(paymentTerm3);

    }

    private void createInvoices(){

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceNo(ImmobelConstants.INV_001);
        invoice1.setStatus(Status.UNPAID);
        invoice1.setPaymentTerm(ImmobelConstants.NET_30);
        this.invoiceService.create(invoice1);

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceNo(ImmobelConstants.INV_002);
        invoice2.setStatus(Status.PAID);
        invoice2.setPaymentTerm(ImmobelConstants.NET_45);
        this.invoiceService.create(invoice2);

        Invoice invoice3 = new Invoice();
        invoice3.setInvoiceNo(ImmobelConstants.INV_003);
        invoice3.setStatus(Status.UNPAID);
        invoice3.setPaymentTerm(ImmobelConstants.NET_30);
        this.invoiceService.create(invoice3);

        Invoice invoice4 = new Invoice();
        invoice4.setInvoiceNo(ImmobelConstants.INV_004);
        invoice4.setStatus(Status.UNPAID);
        invoice4.setPaymentTerm(ImmobelConstants.NET_15);
        this.invoiceService.create(invoice4);

        Invoice invoice5 = new Invoice();
        invoice5.setInvoiceNo(ImmobelConstants.INV_005);
        invoice5.setStatus(Status.UNPAID);
        invoice5.setPaymentTerm(ImmobelConstants.NET_30);
        this.invoiceService.create(invoice5);

    }
}
