package com.dustman.service;

import com.dustman.dto.PaymentDTO;
import com.dustman.model.Payment;
import com.dustman.model.User;
import com.dustman.repository.PaymentRepo;
import com.dustman.repository.UserRepo;
import com.dustman.utils.ResponseData;
import com.dustman.utils.enums.PaymentStatus;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

@Service
public class PaymentService {
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    UserRepo userRepo;

    @Value("${Razorpay.Secret}")
    private String razorpaySecret;
    @Value("${Razorpay.Key}")
    private String razorpayKet;

    private RazorpayClient instance = new RazorpayClient("rzp_test_211d0R55zEAoXB", "XdFXq7o79O8FWFNA0wQXGOET");
    public PaymentService() throws RazorpayException {

    }

    public ResponseData makePayment(PaymentDTO paymentDTO) throws RazorpayException {
//        paymentDTO.toString();
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",paymentDTO.getAmount());
        orderRequest.put("currency","INR");
        orderRequest.put("receipt", paymentDTO.getUserID());
        JSONObject notes = new JSONObject();
        notes.put("notes_key_1", paymentDTO.getItems());
        orderRequest.put("notes",notes);

        Order order = instance.orders.create(orderRequest);
        System.out.println("ORDER=>"+order);
        User user= userRepo.findByEmail(paymentDTO.getUserID()).orElseThrow(()->new NoSuchElementException("No user Exist"));

        JSONObject json = order.toJson();
        Map<String, Object> orderMap = json.toMap();
        Payment payment=new Payment();
        payment.setTransactionId(json.getString("id"));
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setUserId(user);
        payment.setPaymentTime(new Date( json.getLong("created_at")* 1000));
        List<Payment>list=new ArrayList<>();
        list.add(payment);
        user.setPayments(list);
        userRepo.save(user);
        paymentRepo.save(payment);
        return new ResponseData(200, orderMap);
    }

    public ResponseData verifySignature(PaymentDTO paymentDTO) {
        System.out.println("paymentDTO=>"+paymentDTO.toString());
        try {
            String secret = razorpaySecret;
            boolean success;
            String data = paymentDTO.getOrderId() + "|" + paymentDTO.getPaymentId();

            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            byte[] digest = mac.doFinal(data.getBytes());

            String actualSignature = new String(Hex.encodeHex(digest));
            success= actualSignature.equals(paymentDTO.getRazorpaySignature());
            if (success){
              Payment payment=  paymentRepo.findByTransactionId(paymentDTO.getOrderId());
              payment.setPaymentStatus(PaymentStatus.SUCCESS);
              paymentRepo.save(payment);
              return  new ResponseData(200,payment);
            }else {
                return new ResponseData(400,"Invalid payment");
            }
        } catch (Exception e) {
            return new ResponseData(500,e.getMessage());
        }
    }



    public ResponseData getAllPayments() {
        return new ResponseData(200,  paymentRepo.findAll());
    }

    public ResponseData getPaymentsByUser(int userId) {
        return new ResponseData(200,  paymentRepo.findByUserId_UserId(userId));
    }

}
