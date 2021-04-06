package com.immobel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "payment_term")
public class PaymentTerm {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="payment_term_id", unique=true, nullable=false, length=10)
    private Integer paymentTermId;

    @Column(name="code", unique = true)
    private String code;

    @Column(name="description")
    private String description;

    @Column(name="creation_date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date creationDate;

    @Column(name="days")
    private int days;

    @Column(name="remind_before_days")
    private int remindBeforeDays;

}
