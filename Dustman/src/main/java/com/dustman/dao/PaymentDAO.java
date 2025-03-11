package com.dustman.dao;

import com.dustman.dto.payment.PaymentInfo;
import com.dustman.model.PaymentDetails;
import com.dustman.utils.rowmapper.PaymentRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PaymentDAO {

    public final JdbcTemplate jdbcTemplate;

    public PaymentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public boolean makePayment(PaymentInfo paymentInfo) {
        String sql = "INSERT INTO `Dustman`.`payment_details` (`amount`, `status`, `make_payment_time`, `payment_id`, `user_id`) VALUES (?,?,?,?,?)";
        int reasut=jdbcTemplate.update(sql, paymentInfo.amount()
                ,paymentInfo.status().toString(),
                new Timestamp(System.currentTimeMillis()),
                paymentInfo.paymentId(),
                paymentInfo.user());
        return reasut>0;
    }

    public List<PaymentDetails> getAllPaymentInfo() {
        String sql = "SELECT * FROM Dustman.payment_details";
        return jdbcTemplate.query(sql, new PaymentRowMapper());
    }

    public List<PaymentDetails> getPaymentInfoByUserId(String userId) {
        String sql = "SELECT * FROM Dustman.payment_details WHERE user_id = ?";
        return jdbcTemplate.query(sql, new PaymentRowMapper(), userId);
    }
}
