package com.dustman.controller;

import com.dustman.model.Payment;
import com.dustman.service.PaymentService;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('SHOP_SKIPPER')")
    @PostMapping("/make")
    public ResponseEntity<?> pay(@RequestBody Payment payment) {
        ResponseData responseData=paymentService.makePayment(payment);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allpayment")
    public ResponseEntity<?> getAll() {
        ResponseData responseData=paymentService.getAllPayments();
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable int userId) {
        ResponseData responseData=paymentService.getPaymentsByUser(userId);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }
}
