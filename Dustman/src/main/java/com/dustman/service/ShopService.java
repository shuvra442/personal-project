package com.dustman.service;

import com.dustman.model.Shop;

import com.dustman.repository.ShopRepo;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepo shopRepo;

    public ResponseData<?> createUser(Shop shop) {
        if (shop.getShopEmail() != null) {
            Optional<Shop> existingShop = shopRepo.findByShopEmail(shop.getShopEmail());
            if (existingShop.isPresent()) {
                return new ResponseData<>(409, "Shop Email already exists", null);
            }
        }

        Shop savedShop = shopRepo.save(shop);
        return new ResponseData<>(200, "Shop Created", savedShop);
    }

// For get details of shop by ShopId
    public ResponseData<?> getShopById(int shopId) {
        Optional<Shop> shopOptional = shopRepo.findById(shopId);
        if (shopOptional.isPresent()) {
            return new ResponseData<>(200, "Shop found", shopOptional.get());
        } else {
            return new ResponseData<>(404, "Shop not found", null);
        }
    }


// To update the data of shop

    public ResponseData<?> updateShop(int shopId, Shop updatedShop) {
        Optional<Shop> existingShopOptional = shopRepo.findById(shopId);

        if (existingShopOptional.isEmpty()) {
            return new ResponseData<>(404, "Shop not found", null);
        }

        Shop existingShop = existingShopOptional.get();

        // Update fields
        existingShop.setShopEmail(updatedShop.getShopEmail());
        existingShop.setShopImage(updatedShop.getShopImage());
        existingShop.setShopImgId(updatedShop.getShopImgId());
        existingShop.setShopAdd(updatedShop.getShopAdd());
        existingShop.setOnlineStatus(updatedShop.isOnlineStatus());
        existingShop.setGarbageAmt(updatedShop.getGarbageAmt());
        existingShop.setGarbagePrice(updatedShop.getGarbagePrice());
        existingShop.setUserId(updatedShop.getUserId());

        Shop savedShop = shopRepo.save(existingShop);
        return new ResponseData<>(200, "Shop updated successfully", savedShop);
    }



    //To delete the shop
    public ResponseData<?> deleteShop(int shopId) {
        Optional<Shop> shopOptional = shopRepo.findById(shopId);
        if (shopOptional.isEmpty()) {
            return new ResponseData<>(404, "Shop not found", null);
        }

        shopRepo.deleteById(shopId);
        return new ResponseData<>(200, "Shop deleted successfully", null);
    }



}
