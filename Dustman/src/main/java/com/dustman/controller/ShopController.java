package com.dustman.controller;

import com.dustman.dto.AddShopDTO;
import com.dustman.dto.Status;
import com.dustman.service.OrderService;
import com.dustman.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shop")
public class ShopController {

//    private final ShopService shopService;
//    private final OrderService orderService;
//
//
//    public ShopController(ShopService shopService, OrderService orderService) {
//        this.shopService = shopService;
//        this.orderService = orderService;
//    }
//
//    @PatchMapping("/changeonlinestatus")
//    public ResponseEntity<Object> changeOnlineStatus(@RequestPart("id") String id) {
//        System.out.println("Enter");
//        Status status = shopService.changeOnlineStatus(id);
//        return ResponseEntity.status(status.code()).body(status.data());
//    }
//
//
//    @PutMapping("/updateshop")
//    public ResponseEntity<Object> updateShop(@RequestBody AddShopDTO addShopDTO) {
//        Status status = shopService.updateShop(addShopDTO);
//        return ResponseEntity.status(status.code()).body(status.data());
//    }
//
//
//    @DeleteMapping("/deleteshop")
//    public ResponseEntity<Object> deleteShop(@RequestPart("id") String id) {
//        Status status = shopService.deleteShop(id);
//        return ResponseEntity.status(status.code()).body(status.data());
//    }
//
//
//    @PatchMapping("/completeorder")
//    public ResponseEntity<Object> completeOrder(@RequestPart("id") String id) {
//        Status status = orderService.completeOrder(id);
//        return ResponseEntity.status(status.code()).body(status.data());
//    }

}