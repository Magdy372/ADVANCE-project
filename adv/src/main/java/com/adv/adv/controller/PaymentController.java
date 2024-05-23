package com.adv.adv.controller;

import java.util.ArrayList;

import javax.swing.text.html.Option;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.adv.adv.model.Payment;


import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;



public class PaymentController {
     private List<Payment> payments = new ArrayList<>();
    private long nextId = 1;

    @GetMapping
    public List<Payment> getAllPayments() {
        return payments;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = payments.stream().filter(p -> p.getId().equals(id)).findFirst();
        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        payment.setId(nextId++);
        payments.add(payment);
        return payment;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        Optional<Payment> optionalPayment = payments.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setPayerName(paymentDetails.getPayerName());
            payment.setPayerEmail(paymentDetails.getPayerEmail());
            payment.setAmount(paymentDetails.getAmount());
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        Optional<Payment> optionalPayment = payments.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (optionalPayment.isPresent()) {
            payments.remove(optionalPayment.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
