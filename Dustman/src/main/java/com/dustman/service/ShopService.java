package com.dustman.service;

import com.dustman.dao.ShopDAO;
import com.dustman.dto.AddShopDTO;
import com.dustman.dto.Status;
import com.dustman.model.ShopDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final ShopDAO shopDAO;

    public ShopService(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }


    public Status addShop(AddShopDTO addShopDTO) {
        if (shopDAO.addShop(addShopDTO)) {
            return new Status(201, "Shop added successfully");
        } else {
            return new Status(500);
        }
    }

    public Status updateShop(AddShopDTO addShopDTO) {
        if (shopDAO.updateShop(addShopDTO)) {
            return new Status(200, "Shop added successfully");
        }
        return new Status(500);

    }

    public Status getAllShop() {
        List<ShopDetails> shopDetailsList = shopDAO.getAllShop();
        if (!shopDetailsList.isEmpty()) {
            return new Status(200, shopDetailsList);
        }
        return new Status(201);
    }

    public Status changeOnlineStatus(String id) {
        if (shopDAO.changeOnlineStatus(id)) {
            return new Status(200, "Status changed successfully");
        }
        return new Status(500);
    }

    public Status changeActivateStatus(String id) {
        if (shopDAO.changeActivateStatus(id)) {
            return new Status(200, "Status changed successfully");
        }
        return new Status(500);
    }

    public Status activeAndOnlineShop() {
        List<ShopDetails> shopDetailsList = shopDAO.activeAndOnlineShop();
        if (!shopDetailsList.isEmpty()) {
            return new Status(200, shopDetailsList);
        }
        return new Status(204);
    }

    public Status deleteShop(String id) {
        if (shopDAO.deleteShop(id)) {
            return new Status(200, "Shop deleted successfully");
        }
        return new Status(500);

    }
}
