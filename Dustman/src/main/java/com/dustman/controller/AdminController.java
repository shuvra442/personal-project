package com.dustman.controller;

import com.dustman.dto.Status;
import com.dustman.service.OrderService;
import com.dustman.service.PaymentService;
import com.dustman.service.ShopService;
import com.dustman.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final ShopService shopService;
    private final OrderService orderService;


    public AdminController(PaymentService paymentService, UserService userService, ShopService shopService, OrderService orderService) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.shopService = shopService;
        this.orderService = orderService;
    }

    @GetMapping("/showalluserpayment")
    public ResponseEntity<Object> showUserPayment() {
        Status status = paymentService.getAllUserPaymentInfo();
        return ResponseEntity.status(status.code()).body(status.data());

    }


    @GetMapping("/showalluser")
    public ResponseEntity<Object> showAllUser() {
        Status userDetails = userService.getAllUser();
        return ResponseEntity.status(userDetails.code()).body(userDetails.data());
    }

    @PatchMapping("/changeactivatestatus")
    public ResponseEntity<Object> changeActivateStatus(@RequestPart("id") String id) {
        Status status = shopService.changeActivateStatus(id);
        return ResponseEntity.status(status.code()).body(status.data());
    }

    @GetMapping("/allorder")
    public ResponseEntity<?> showAllOrder() {
        Status status = orderService.showAllOrder();
        return ResponseEntity.status(status.code()).body(status.data());
    }


}
