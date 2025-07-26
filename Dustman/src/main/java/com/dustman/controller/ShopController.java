package com.dustman.controller;


import com.dustman.model.Shop;
import com.dustman.service.ShopService;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {


    @Autowired
    ShopService shopService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Shop shop) {
        ResponseData responseData=shopService.createShop(shop);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/allshops")
    public ResponseEntity<?> getAll() {
        ResponseData responseData=shopService.getAllShops();
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @PreAuthorize("hasRole('USER') or hasRole('SHOP_SKIPPER')")
    @GetMapping("/shop/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        ResponseData responseData=shopService.getShopById(id);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @PreAuthorize("hasRole('SHOP_SKIPPER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Shop shop) {
        ResponseData responseData=shopService.updateShop(id, shop);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @PreAuthorize("hasRole('SHOP_SKIPPER') or hasRole('ADMIN') ")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        ResponseData responseData=shopService.deleteShop(id);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }
}
