package com.dustman.controller;


import com.dustman.dto.*;
import com.dustman.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/secure")
public class SecureController {

    private final UserService userService;
    private final PaymentService paymentService;
    private final ShopService shopService;
    private final OrderService orderService;

    public SecureController(UserService userService, PaymentService paymentService, ShopService shopService, OrderService orderService) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.shopService = shopService;
        this.orderService = orderService;
    }

    @GetMapping("/showuserbyid")
    public ResponseEntity<Object> showUserById(@RequestPart("id") String id, @RequestHeader("Authorization") String jwt) {
        String jwtToken=jwt.substring(7);
        Status status = userService.getUserById(id,jwtToken);
        return ResponseEntity.status(status.code()).body(status.data());
    }

    @PutMapping("/updateuser")
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO updatedUserInfo, @RequestHeader("Authorization") String jwt) {
        String jwtToken=jwt.substring(7);
        Status status = userService.updateUser(updatedUserInfo,jwtToken);
        return ResponseEntity.status(status.code()).body(status.data());
    }

    @PatchMapping("/updatepassword")
    public ResponseEntity<Object> updatePassword(@RequestBody UserDTO updatePasswordInfo, @RequestHeader("Authorization") String jwt) {
        String jwtToken=jwt.substring(7);
        Status status = userService.updatePassword(updatePasswordInfo,jwtToken);
        return ResponseEntity.status(status.code()).body(status.data());
    }

//    @PatchMapping("/updaterole")
//    public ResponseEntity<String> updateRole(@RequestBody UserDTO updateRole) {
//        return ResponseEntity.ok(userService.updateRole(updateRole));
//    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<Object> deleteUser(@RequestPart("id") String id, @RequestHeader("Authorization") String jwt) {
        String jwtToken=jwt.substring(7);
        Status status = userService.deleteUser(id,jwtToken);
        return ResponseEntity.status(status.code()).body(status.data());
    }
    //user

    //Payment
    @PostMapping("/payment")
    public ResponseEntity<Object> payment(@RequestBody PaymentInfo paymentInfo) {
        Status status = paymentService.makePayment(paymentInfo);
        return ResponseEntity.status(status.code()).body(status.data());
    }


    @GetMapping("/showuserpayment")
    public ResponseEntity<Object> showUserPayment(@RequestPart("id") String userId) {
        Status status = paymentService.getPaymentInfoByUserId(userId);
        return ResponseEntity.status(status.code()).body(status.data());
    }
    //payment

    //shop
    @PostMapping("/addshop")
    public ResponseEntity<Object> addShop(@RequestBody AddShopDTO addShopDTO) {
        Status status = shopService.addShop(addShopDTO);
        return ResponseEntity.status(status.code()).body(status.data());

    }

    @GetMapping("/showallshop")
    public ResponseEntity<Object> showAllShop() {
        Status status = shopService.getAllShop();
        return ResponseEntity.status(status.code()).body(status.data());
    }


    @GetMapping("/activeandonlineshops")
    public ResponseEntity<Object> activeAndOnlineShop() {
        Status status = shopService.activeAndOnlineShop();

        return ResponseEntity.status(status.code()).body(status.data());
    }


    //shop

    //order
    @PostMapping("/order")
    public ResponseEntity<Object> order(@RequestBody OrderDTO orderDTO) {
        Status status = orderService.addOrder(orderDTO);
        return ResponseEntity.status(status.code()).body(status.data());
    }

    @PatchMapping("/cancelorder")
    public ResponseEntity<Object> cancelOrder(@RequestPart("orderID") String orderID,@RequestPart("userID") String userID) {
        Status status = orderService.cancelOrder(orderID,userID);
        return ResponseEntity.status(status.code()).body(status.data());
    }

    @GetMapping("/userorder")
    public ResponseEntity<Object> showUserOrder(@RequestPart("id") String id) {
        Status status = orderService.showUserOrder(id);
        return ResponseEntity.status(status.code()).body(status.data());
    }

    @GetMapping("/checkorder")
    public ResponseEntity<?> checkOrder(@RequestPart("id") String id) {
        Status status = orderService.checkOrder(id);

        return ResponseEntity.status(status.code()).body(status.data());
    }

}
