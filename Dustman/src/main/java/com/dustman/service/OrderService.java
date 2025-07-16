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

    public ResponseData placeOrder(Order order) {
        return new ResponseData(200,  orderRepo.save(order));
    }

    public ResponseData getAllOrders() {
        return new ResponseData(200,  orderRepo.findAll());
    }

    public ResponseData getOrderById(int id) {
        return orderRepo.findById(id)
                .map(order -> new ResponseData(200,  order))
                .orElseGet(() -> new ResponseData(400, "Order not found"));
    }

    public ResponseData getOrdersByUserId(int userId) {
        return new ResponseData(200,  orderRepo.findByUserId_UserId(userId));
    }
}
