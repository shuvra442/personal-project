package com.dustman.service;

import com.dustman.model.Payment;
import com.dustman.repository.PaymentRepo;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo paymentRepo;

    public ResponseData makePayment(Payment payment) {
        return new ResponseData(200, paymentRepo.save(payment));
    }

    public ResponseData getAllPayments() {
        return new ResponseData(200,  paymentRepo.findAll());
    }

    public ResponseData getPaymentsByUser(int userId) {
        return new ResponseData(200,  paymentRepo.findByUserId_UserId(userId));
    }

}
