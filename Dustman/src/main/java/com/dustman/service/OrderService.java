package com.dustman.service;

import com.dustman.dao.OrderDAO;
import com.dustman.dto.OrderDTO;
import com.dustman.dto.Status;
import com.dustman.model.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public Status addOrder(OrderDTO orderDTO) {
        if (orderDAO.addOrder(orderDTO)) {
            return new Status(200, "Order added successfully");
        } else {
            return new Status(500);
        }
    }

    public Status cancelOrder(String id) {
        if (orderDAO.cancelOrder(id)) {
            return new Status(200, "Order cancelled successfully");
        }
        return new Status(500);
    }

    public Status completeOrder(String id) {
        if (orderDAO.completeOrder(id)) {
            return new Status(200, "Order completed successfully");
        }
        return new Status(500);
    }

    public Status showAllOrder() {
        List<OrderDetails> orderDetailsList = orderDAO.showAllOrder();
        if (!orderDetailsList.isEmpty()) {
            return new Status(200, orderDetailsList);
        }
        return new Status(204);
    }

    public Status showUserOrder(String id) {
        List<OrderDetails> orderDetailsList = orderDAO.showUserOrder(id);
        if (!orderDetailsList.isEmpty()) {
            return new Status(200, orderDetailsList);
        }
        return new Status(204);
    }

    public Status checkOrder(String id) {
        OrderDetails orderDetails= orderDAO.checkOrder(id);
        if (orderDetails != null) {
            return new Status(200, orderDetails);
        }
        return new Status(204);
    }
}
