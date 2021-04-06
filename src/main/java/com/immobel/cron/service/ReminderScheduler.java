package com.immobel.cron.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.immobel.model.Invoice;
import com.immobel.service.InvoiceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReminderScheduler {

    @Autowired
    private InvoiceService invoiceService;

    //@Scheduled(cron = "0 0 * * * ?")
    @Scheduled(cron = "0 */1 * * * ?") // at every minute to test it out
    public void processInvoiceReminders(){
    	log.info("Cron Started at {}", new Date());
        List<Invoice> invoices = this.invoiceService.findAllInvoicesPendingForReminder();
        if(Objects.nonNull(invoices)) {
            for (Invoice invoice : invoices) {
                log.info("Reminder sent for Invoice {}", invoice.getInvoiceNo());
            }
        }
    }
}
