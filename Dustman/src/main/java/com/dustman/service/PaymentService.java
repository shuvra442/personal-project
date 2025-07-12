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

    public ResponseData<Payment> makePayment(Payment payment) {
        return new ResponseData<>(200, "Payment successful", paymentRepo.save(payment));
    }

    public ResponseData<List<Payment>> getAllPayments() {
        return new ResponseData<>(200, "Payment list fetched", paymentRepo.findAll());
    }

    public ResponseData<List<Payment>> getPaymentsByUser(int userId) {
        return new ResponseData<>(200, "User payments fetched", paymentRepo.findByUserId_UserId(userId));
    }

}
