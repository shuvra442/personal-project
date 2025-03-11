package com.dustman.utils.rowmapper;

import com.dustman.model.OrderDetails;
import com.dustman.model.UserDetails;
import com.dustman.utils.OrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderDetails> {

    @Override
    public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

        OrderDetails orderDetails = new OrderDetails();
        UserDetails userDetails = new UserDetails();
        UserDetails userDetails1 = new UserDetails();

        orderDetails.setOrderId(rs.getString("order_id"));
        orderDetails.setGarbageQuantity(rs.getString("garbage_quantity"));
        orderDetails.setStatus(OrderStatus.valueOf(rs.getString("status")));


        userDetails.setUserId(rs.getString("order_from_user_id"));
        orderDetails.setOrderFrom(userDetails);

        userDetails1.setUserId(rs.getString("order_to_user_id"));
        orderDetails.setOrderTo(userDetails1);

        return orderDetails;
    }

}
