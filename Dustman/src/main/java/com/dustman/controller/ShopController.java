package com.dustman.controller;


import com.dustman.model.Shop;
import com.dustman.service.ShopService;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {


    @Autowired
    ShopService shopService;

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Shop>> create(@RequestBody Shop shop) {
        return ResponseEntity.ok(shopService.createShop(shop));
    }

    @GetMapping()
    public ResponseEntity<ResponseData<?>> getAll() {
        return ResponseEntity.ok(shopService.getAllShops());
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<ResponseData<?>> getById(@PathVariable int id) {
        return ResponseEntity.ok(shopService.getShopById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData<?>> update(@PathVariable int id, @RequestBody Shop shop) {
        return ResponseEntity.ok(shopService.updateShop(id, shop));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData<?>> delete(@PathVariable int id) {
        return ResponseEntity.ok(shopService.deleteShop(id));
    }
}
