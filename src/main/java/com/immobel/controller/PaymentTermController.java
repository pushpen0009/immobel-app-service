package com.immobel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.immobel.model.PaymentTerm;
import com.immobel.service.PaymentTermService;

@Controller
@RequestMapping("/payment-term")
public class PaymentTermController {

    @Autowired
    private PaymentTermService paymentTermService;

    @PostMapping
    public ResponseEntity<PaymentTerm> createPaymentTerm(@RequestBody PaymentTerm paymentTerm){
        return new ResponseEntity<>(this.paymentTermService.create(paymentTerm), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PaymentTerm> updatePaymentTerm(@RequestBody PaymentTerm paymentTerm){
        return new ResponseEntity<>(this.paymentTermService.update(paymentTerm), HttpStatus.OK);
    }

    @GetMapping("/{paymentTermId}")
    public ResponseEntity<PaymentTerm> findPaymentTerm(@PathVariable int paymentTermId){
        return new ResponseEntity<>(this.paymentTermService.get(paymentTermId), HttpStatus.OK);
    }

    @DeleteMapping("/{paymentTermId}")
    public ResponseEntity<String> deletePaymentTerm(@PathVariable int paymentTermId){
        this.paymentTermService.delete(paymentTermId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentTerm>> findAllPaymentTerm(){
        return new ResponseEntity<>(this.paymentTermService.findAll(), HttpStatus.OK);
    }
}
