package com.dustman.controller;

import com.dustman.dto.PaymentDTO;
import com.dustman.model.Payment;
import com.dustman.service.PaymentService;
import com.dustman.utils.ResponseData;
import com.dustman.utils.jwt.JWTCreate;
import com.razorpay.RazorpayException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private JWTCreate jwtCreate;


//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('SHOP_SKIPPER')")
    @PostMapping("/make")
    public ResponseEntity<?> pay(@RequestBody PaymentDTO payment, HttpServletRequest request) throws RazorpayException {
        String token= jwtCreate.extractToken(request.getCookies());
        String email=jwtCreate.extractUserName(token);
        payment.setUserID(email);
        ResponseData responseData=paymentService.makePayment(payment);
        System.out.println("responseData.data()=>"+responseData.data());
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifySignature(@RequestBody PaymentDTO payment, HttpServletRequest request) throws RazorpayException {
        String token= jwtCreate.extractToken(request.getCookies());
        String email=jwtCreate.extractUserName(token);
        payment.setUserID(email);
        ResponseData responseData=paymentService.verifySignature(payment);
        System.out.println("responseData.data()=>"+responseData.data());
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
