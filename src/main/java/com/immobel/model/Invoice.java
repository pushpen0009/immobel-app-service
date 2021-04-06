package com.immobel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="invoice_id", unique=true, nullable=false, length=10)
    private Integer invoiceId;

    @Column(name="invoice_no", unique = true)
    private String invoiceNo;

    @Column(name="invoice_date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    @Column(name="payment_term")
    private String paymentTerm;

    @Column(name="status")
    private Status status;

}
