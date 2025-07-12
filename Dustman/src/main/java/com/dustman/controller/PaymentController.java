package com.dustman.controller;

import com.dustman.model.Payment;
import com.dustman.service.PaymentService;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/make")
    public ResponseEntity<ResponseData<Payment>> pay(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.makePayment(payment));
    }

    @GetMapping
    public ResponseEntity<ResponseData<?>> getAll() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseData<?>> getByUser(@PathVariable int userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUser(userId));
    }
}
