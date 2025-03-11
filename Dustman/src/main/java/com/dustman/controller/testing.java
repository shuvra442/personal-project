package com.dustman.controller;


import com.dustman.dto.order.OrderDTO;
import com.dustman.dto.payment.PaymentInfo;
import com.dustman.dto.shop.AddShopDTO;
import com.dustman.dto.user.*;
import com.dustman.model.OrderDetails;
import com.dustman.model.PaymentDetails;
import com.dustman.model.ShopDetails;
import com.dustman.model.UserDetails;
import com.dustman.service.OrderService;
import com.dustman.service.PaymentService;
import com.dustman.service.ShopService;
import com.dustman.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class testing {

    private final UserService userService;
    private final PaymentService paymentService;
    private final ShopService shopService;
    private final OrderService orderService;

    public testing(UserService userService, PaymentService paymentService, ShopService shopService, OrderService orderService) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.shopService = shopService;
        this.orderService = orderService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody RegisterInfo registerInfo) {
        String result = userService.registerUser(registerInfo);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginInfo loginInfo) {
        return true;
    }

    @GetMapping("/showalluser")         //TODO: need to update for clean response
    public ResponseEntity<List<UserDetails>> showAllUser() {
        List<UserDetails> userDetails = userService.getAllUser();
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/showuserbyid")
    public ResponseEntity<?> showUserById(@RequestPart("id") String id) {
        if (!userService.checkUser(id)) {
            return ResponseEntity.badRequest().body("User not found");
        } else {
            return ResponseEntity.ok(userService.getUserById(id));
        }
    }

    @PutMapping("/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody UpdatedUserInfo updatedUserInfo) {
        if (!userService.checkUser(updatedUserInfo.getUserId())) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(userService.updateUser(updatedUserInfo));
    }

    @PatchMapping("/updatepassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordInfo updatePasswordInfo) {
        return ResponseEntity.ok(userService.updatePassword(updatePasswordInfo));
    }

    @PatchMapping("/updaterole")
    public ResponseEntity<String> updateRole(@RequestBody UpdateRole updateRole) {
        return ResponseEntity.ok(userService.updateRole(updateRole));
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<String> deleteUser(@RequestPart("id") String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    //user


    //Payment
    @PostMapping("/payment")
    public ResponseEntity<String> payment(@RequestBody PaymentInfo paymentInfo) {
        String result = paymentService.makePayment(paymentInfo);
        if (result.equals("success")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }

    }

    @GetMapping("/showalluserpayment")
    public ResponseEntity<List<PaymentDetails>> showUserPayment() {
        List<PaymentDetails> details = paymentService.getAllUserPaymentInfo();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/showuserpayment")
    public ResponseEntity<List<PaymentDetails>> showUserPayment(@RequestPart("id") String userId) {
        List<PaymentDetails> details = paymentService.getPaymentInfoByUserId(userId);
        return ResponseEntity.ok(details);
    }
    //payment


    //shop
    @PostMapping("/addshop")
    public ResponseEntity<?> addShop(@RequestBody AddShopDTO addShopDTO) {
        return ResponseEntity.ok(shopService.addShop(addShopDTO));

    }

    @GetMapping("/showallshop")
    public ResponseEntity<List<ShopDetails>> showAllShop() {
        List<ShopDetails> shopDetails = shopService.getAllShop();
        return ResponseEntity.ok(shopDetails);
    }

    @PatchMapping("/changeonlinestatus")
    public ResponseEntity<String> changeOnlineStatus(@RequestPart("id") String id) {
        return ResponseEntity.ok(shopService.changeOnlineStatus(id));
    }

    @PatchMapping("/changeactivatestatus")
    public ResponseEntity<String> changeActivateStatus(@RequestPart("id") String id) {
        return ResponseEntity.ok(shopService.changeActivateStatus(id));
    }

    @GetMapping("/activeandonlineshops")
    public ResponseEntity<?> activeAndOnlineShop() {
        List<ShopDetails> shopDetails = shopService.activeAndOnlineShop();
        if (shopDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shopDetails);
    }

    @PutMapping("/updateshop")
    public ResponseEntity<?> updateShop(@RequestBody AddShopDTO addShopDTO) {
        return ResponseEntity.ok(shopService.updateShop(addShopDTO));
    }

    @DeleteMapping("/deleteshop")
    public ResponseEntity<String> deleteShop(@RequestPart("id") String id) {
        return ResponseEntity.ok(shopService.deleteShop(id));
    }
    //shop

    //order
    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.addOrder(orderDTO));
    }

    @PatchMapping("/cancelorder")
    public ResponseEntity<String> cancelOrder(@RequestPart("id") String id) {
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }

    @PatchMapping("/completeorder")
    public ResponseEntity<String> completeOrder(@RequestPart("id") String id) {
        return ResponseEntity.ok(orderService.completeOrder(id));
    }

    @GetMapping("/allorder")
    public ResponseEntity<?> showAllOrder() {
        List<OrderDetails> orderDetails = orderService.showAllOrder();
        if (orderDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/userorder")
    public ResponseEntity<?> showUserOrder(@RequestPart("id") String id) {
        List<OrderDetails> orderDetails = orderService.showUserOrder(id);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderDetails);
    }

    @GetMapping("/checkorder")
    public ResponseEntity<?> checkOrder(@RequestPart("id") String id) {
        OrderDetails orderDetails = orderService.checkOrder(id);
        if (orderDetails==null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderDetails);
    }
}
