package com.dustman.service;


import com.dustman.model.Order;
import com.dustman.repository.OrderRepo;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    public ResponseData<Order> placeOrder(Order order) {
        return new ResponseData<>(200, "Order placed", orderRepo.save(order));
    }

    public ResponseData<List<Order>> getAllOrders() {
        return new ResponseData<>(200, "Order list fetched", orderRepo.findAll());
    }

    public ResponseData<Order> getOrderById(int id) {
        return orderRepo.findById(id)
                .map(order -> new ResponseData<>(200, "Order found", order))
                .orElseGet(() -> new ResponseData<>(400, "Order not found", null));
    }

    public ResponseData<List<Order>> getOrdersByUserId(int userId) {
        return new ResponseData<>(200, "Orders fetched", orderRepo.findByUserId_UserId(userId));
    }
}
