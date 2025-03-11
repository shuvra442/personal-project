package com.dustman.dao;

import com.dustman.dto.order.OrderDTO;
import com.dustman.model.OrderDetails;
import com.dustman.utils.OrderStatus;
import com.dustman.utils.rowmapper.OrderRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDAO {
    private final JdbcTemplate jdbcTemplate;

    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean addOrder(OrderDTO orderDTO) {
        String sql = "INSERT INTO `Dustman`.`order_details` (`status`, `garbage_quantity`, `order_from_user_id`, `order_id`, `order_to_user_id`) VALUES (?,?,?,?,?)";
        int result = jdbcTemplate.update(sql, orderDTO.getStatus().toString(), orderDTO.getGarbageQuantity(), orderDTO.getOrderFrom(), UUID.randomUUID().toString(), orderDTO.getOrderTo());
        return result > 0;
    }

    public boolean cancelOrder(String id) {
        String sql = "UPDATE `Dustman`.`order_details` SET `status` = ? WHERE (`order_id` = ?)";
        int result = jdbcTemplate.update(sql, OrderStatus.CANCELLED.toString(), id);
        return result > 0;
    }

    public boolean completeOrder(String id) {
        String sql = "UPDATE `Dustman`.`order_details` SET `status` = ? WHERE (`order_id` = ?)";
        int result = jdbcTemplate.update(sql, OrderStatus.COMPLETED.toString(), id);
        return result > 0;
    }

    public List<OrderDetails> showAllOrder() {
        String sql = "SELECT * FROM Dustman.order_details";
        List<OrderDetails> orderDetails = jdbcTemplate.query(sql, new OrderRowMapper());
        return orderDetails;
    }

    public List<OrderDetails> showUserOrder(String id) {
        List<OrderDetails> orderDetails;
        String sql1 = "SELECT * FROM Dustman.order_details WHERE order_from_user_id = ?";
        orderDetails = jdbcTemplate.query(sql1, new OrderRowMapper(), id);
        if (orderDetails.isEmpty()) {
            String sql2 = "SELECT * FROM Dustman.order_details WHERE order_to_user_id = ?";
            orderDetails = jdbcTemplate.query(sql2, new OrderRowMapper(), id);
        }
        return orderDetails;
    }

    public OrderDetails checkOrder(String id) {
        String sql1 = "SELECT * FROM Dustman.order_details WHERE order_id = ?";
        OrderDetails orderDetails = jdbcTemplate.queryForObject(sql1, new OrderRowMapper(), id);
        return orderDetails;
    }


}
