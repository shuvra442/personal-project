package com.dustman.utils.rowmapper;

import com.dustman.model.PaymentDetails;
import com.dustman.model.UserDetails;
import com.dustman.utils.PaymentStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<PaymentDetails> {
    @Override
    public PaymentDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

        PaymentDetails paymentDetails = new PaymentDetails();

        paymentDetails.setPaymentId(rs.getString("payment_id"));
        String status=rs.getString("status");
        paymentDetails.setStatus(PaymentStatus.valueOf(status));
        paymentDetails.setAmount(rs.getDouble("amount"));
        paymentDetails.setMakePaymentTime(rs.getTimestamp("make_payment_time"));
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(rs.getString("user_id"));

        paymentDetails.setUser(userDetails);

        return paymentDetails;
    }
}
