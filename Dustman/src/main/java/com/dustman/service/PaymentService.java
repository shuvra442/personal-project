package com.dustman.service;

import com.dustman.dao.PaymentDAO;
import com.dustman.dto.payment.PaymentInfo;
import com.dustman.model.PaymentDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public String makePayment(PaymentInfo paymentInfo) {
        boolean status=paymentDAO.makePayment(paymentInfo);
        return status?"success":"fail";
    }

    public List<PaymentDetails> getAllUserPaymentInfo() {
        return paymentDAO.getAllPaymentInfo();
    }

    public  List<PaymentDetails> getPaymentInfoByUserId(String userId){
        return paymentDAO.getPaymentInfoByUserId(userId);
    }
}
