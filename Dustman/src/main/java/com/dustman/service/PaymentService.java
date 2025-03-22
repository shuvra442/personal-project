package com.dustman.service;

import com.dustman.dao.PaymentDAO;
import com.dustman.dao.UserDAO;
import com.dustman.dto.PaymentInfo;
import com.dustman.dto.Status;
import com.dustman.model.PaymentDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentDAO paymentDAO;
    private final UserDAO userDAO;

    public PaymentService(PaymentDAO paymentDAO, UserDAO userDAO) {
        this.paymentDAO = paymentDAO;
        this.userDAO = userDAO;
    }

    public Status makePayment(PaymentInfo paymentInfo) {
        boolean status = paymentDAO.makePayment(paymentInfo);
        return status ? new Status(200) : new Status(500);
    }

    public Status getAllUserPaymentInfo() {
        List<PaymentDetails> paymentDetailsList = paymentDAO.getAllPaymentInfo();
        if (!paymentDetailsList.isEmpty()) {
            return new Status(200, paymentDetailsList);
        }
        return new Status(204);
    }

    public Status getPaymentInfoByUserId(String userId) {
        if (userDAO.checkUser(userId)) {
            List<PaymentDetails> paymentDetailsList = paymentDAO.getPaymentInfoByUserId(userId);
            if (paymentDetailsList.isEmpty()){
                return new Status(204);
            }
            return new Status(200,paymentDetailsList);
        }
        return new Status(400,"Invalid User ID");
    }
}
