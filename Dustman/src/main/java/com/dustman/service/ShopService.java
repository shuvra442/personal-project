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

    public ResponseData createShop(Shop shop) {
        Shop saved = shopRepo.save(shop);
        return new ResponseData(200, saved);
    }

    public ResponseData getAllShops() {

        return new ResponseData(200, shopRepo.findAll());
    }

    public ResponseData getShopById(int id) {
        return shopRepo.findById(id)
                .map(shop -> new ResponseData(200, shop))
                .orElseGet(() -> new ResponseData(400, "Shop not found"));
    }

    public ResponseData updateShop(int id, Shop updatedShop) {
        Optional<Shop> optional = shopRepo.findById(id);
        if (optional.isEmpty()) {
            return new ResponseData(400, "Shop not found");
        }

        Shop shop = optional.get();
        shop.setShopAdd(updatedShop.getShopAdd());
        shop.setOnlineStatus(updatedShop.isOnlineStatus());
        shop.setGarbageAmt(updatedShop.getGarbageAmt());
        shop.setGarbagePrice(updatedShop.getGarbagePrice());

        return new ResponseData(200,  shopRepo.save(shop));
    }

    public ResponseData deleteShop(int id) {
        if (!shopRepo.existsById(id)) {
            return new ResponseData(400, "Shop not found");
        }
        shopRepo.deleteById(id);
        return new ResponseData(200, "Shop deleted");
    }
}
