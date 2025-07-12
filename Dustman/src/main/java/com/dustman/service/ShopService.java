package com.dustman.service;

import com.dustman.model.Shop;
import com.dustman.repository.ShopRepo;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    ShopRepo shopRepo;

    public ResponseData<Shop> createShop(Shop shop) {
        Shop saved = shopRepo.save(shop);
        return new ResponseData<>(200, "Shop created successfully", saved);
    }

    public ResponseData<List<Shop>> getAllShops() {

        return new ResponseData<>(200, "Shop list fetched", shopRepo.findAll());
    }

    public ResponseData<Shop> getShopById(int id) {
        return shopRepo.findById(id)
                .map(shop -> new ResponseData<>(200, "Shop found", shop))
                .orElseGet(() -> new ResponseData<>(400, "Shop not found", null));
    }

    public ResponseData<Shop> updateShop(int id, Shop updatedShop) {
        Optional<Shop> optional = shopRepo.findById(id);
        if (optional.isEmpty()) {
            return new ResponseData<>(400, "Shop not found", null);
        }

        Shop shop = optional.get();
        shop.setShopAdd(updatedShop.getShopAdd());
        shop.setOnlineStatus(updatedShop.isOnlineStatus());
        shop.setGarbageAmt(updatedShop.getGarbageAmt());
        shop.setGarbagePrice(updatedShop.getGarbagePrice());

        return new ResponseData<>(200, "Shop updated", shopRepo.save(shop));
    }

    public ResponseData<?> deleteShop(int id) {
        if (!shopRepo.existsById(id)) {
            return new ResponseData<>(400, "Shop not found", null);
        }
        shopRepo.deleteById(id);
        return new ResponseData<>(200, "Shop deleted", null);
    }
}
