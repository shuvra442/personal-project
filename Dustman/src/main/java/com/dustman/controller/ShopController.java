package com.dustman.controller;


import com.dustman.model.Shop;

import com.dustman.service.ShopService;

import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShopController {

    @Autowired
    private ShopService shopService;

    //To Create Shop
    @PostMapping("/createShop")
    public ResponseEntity<?> createShop(@RequestBody Shop shop) {
        ResponseData<?> responseData = shopService.createUser(shop);
        return ResponseEntity.status(responseData.getStatus()).body(responseData.getData());
    }

    // To get all details of shop by id
    @GetMapping("/shop/{id}")
    public ResponseEntity<?> getShopById(@PathVariable("id") int shopId) {
        ResponseData<?> responseData = shopService.getShopById(shopId);
        return ResponseEntity.status(responseData.getStatus()).body(responseData.getData());
    }

    // To update Shop details by shop id
    @PutMapping("/shop/{id}")
    public ResponseEntity<?> updateShop(@PathVariable("id") int shopId, @RequestBody Shop shop) {
        ResponseData<?> responseData = shopService.updateShop(shopId, shop);
        return ResponseEntity.status(responseData.getStatus()).body(responseData.getData());
    }


    // To delete the Shop by shopId
    @DeleteMapping("/shop/{id}")
    public ResponseEntity<?> deleteShop(@PathVariable("id") int shopId) {
        ResponseData<?> responseData = shopService.deleteShop(shopId);
        return ResponseEntity.status(responseData.getStatus()).body(responseData.getData());
    }


}

