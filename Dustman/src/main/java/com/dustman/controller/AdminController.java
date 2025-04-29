package com.dustman.controller;

import com.dustman.dto.*;
import com.dustman.service.*;
import com.dustman.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/admin")
public class AdminController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final ShopService shopService;
    private final OrderService orderService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AdminController(PaymentService paymentService, UserService userService, ShopService shopService, OrderService orderService, EmailService emailService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.shopService = shopService;
        this.orderService = orderService;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
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

    @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody UserDTO registerInfo) {
        Status result = userService.registerUser(registerInfo);
        return ResponseEntity.status(result.code()).body(result.data());
    }

    @PostMapping("/user_login")
    public ResponseEntity<String> login(@RequestBody UserDTO loginInfo) {
        String userName= loginInfo.getUsername();
        String password= loginInfo.getPassword();
        System.out.println("Enter into login");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("username and Password is incorrect");
        }

        UserDetails userDetails= userDetailsService.loadUserByUsername(userName);
        String jwt= jwtUtil.generateJWT(userDetails);

        return ResponseEntity.ok(jwt);
    }

    @PatchMapping("/updaterole")
    public ResponseEntity<String> updateRole(@RequestBody UserDTO updateRole) {
        System.out.println(updateRole.getUserId());
        return ResponseEntity.ok(userService.updateRole(updateRole));
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

    //shop


    @PatchMapping("/changeonlinestatus")
    public ResponseEntity<Object> changeOnlineStatus(@RequestPart("id") String id) {
        System.out.println("Enter");
        Status status = shopService.changeOnlineStatus(id);
        return ResponseEntity.status(status.code()).body(status.data());
    }


    @PutMapping("/updateshop")
    public ResponseEntity<Object> updateShop(@RequestBody AddShopDTO addShopDTO) {
        Status status = shopService.updateShop(addShopDTO);
        return ResponseEntity.status(status.code()).body(status.data());
    }


    @DeleteMapping("/deleteshop")
    public ResponseEntity<Object> deleteShop(@RequestPart("id") String id) {
        Status status = shopService.deleteShop(id);
        return ResponseEntity.status(status.code()).body(status.data());
    }


    @PatchMapping("/completeorder")
    public ResponseEntity<Object> completeOrder(@RequestPart("id") String id) {
        Status status = orderService.completeOrder(id);
        return ResponseEntity.status(status.code()).body(status.data());
    }


}
