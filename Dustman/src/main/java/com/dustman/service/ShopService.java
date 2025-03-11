package com.dustman.service;

import com.dustman.dao.ShopDAO;
import com.dustman.dto.shop.AddShopDTO;
import com.dustman.model.ShopDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopDAO shopDAO;

    public ShopService(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }


    public String addShop(AddShopDTO addShopDTO) {
        if (shopDAO.addShop(addShopDTO)) {
            return "Shop added successfully";
        } else {
            return "Failed to add shop";
        }
    }

    public String updateShop(AddShopDTO addShopDTO) {
        if (shopDAO.updateShop(addShopDTO)) {
            return "Shop added successfully";
        } else {
            return "Failed to add shop";
        }
    }

    public List<ShopDetails> getAllShop() {

        return shopDAO.getAllShop();
    }

    public String changeOnlineStatus(String id) {
        return shopDAO.changeOnlineStatus(id);
    }

    public String changeActivateStatus(String id) {
        return shopDAO.changeActivateStatus(id);
    }

    public List<ShopDetails> activeAndOnlineShop() {
        return shopDAO.activeAndOnlineShop();
    }

    public String deleteShop(String id) {
        return shopDAO.deleteShop(id);
    }
}
