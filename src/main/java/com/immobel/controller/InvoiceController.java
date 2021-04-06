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

import com.immobel.model.Invoice;
import com.immobel.service.InvoiceService;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice){
        return new ResponseEntity<>(this.invoiceService.create(invoice), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice){
        return new ResponseEntity<>(this.invoiceService.update(invoice), HttpStatus.OK);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<Invoice> findInvoice(@PathVariable int invoiceId){
        return new ResponseEntity<>(this.invoiceService.get(invoiceId), HttpStatus.OK);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Integer invoiceId){
        this.invoiceService.delete(invoiceId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> findAllInvoice(){
        return new ResponseEntity<>(this.invoiceService.findAll(), HttpStatus.OK);
    }

}
