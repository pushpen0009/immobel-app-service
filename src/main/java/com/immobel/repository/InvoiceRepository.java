package com.immobel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.immobel.model.Invoice;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(value = "SELECT inv.* from invoice inv inner join payment_term pt on inv.payment_term = pt.code " +
            " where DATEDIFF('DAY',current_date(), DATEADD(DAY, pt.DAYS, inv.invoice_date)) = pt.remind_before_days and inv.status=1", nativeQuery = true)
    List<Invoice> findAllInvoicesPendingForReminder();
}
