package com.dustman.controller;

import com.dustman.model.Order;
import com.dustman.service.OrderService;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<?> place(@RequestBody Order order) {
        ResponseData responseData=orderService.placeOrder(order);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ResponseData responseData=orderService.getAllOrders();
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        ResponseData responseData=orderService.getOrderById(id);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable int userId) {
        ResponseData responseData=orderService.getOrdersByUserId(userId);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }
}
