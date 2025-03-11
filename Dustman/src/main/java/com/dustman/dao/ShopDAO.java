package com.dustman.dao;


import com.dustman.dto.shop.AddShopDTO;
import com.dustman.dto.user.UpdateRole;
import com.dustman.model.ShopDetails;
import com.dustman.utils.Role;
import com.dustman.utils.rowmapper.ShopRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class ShopDAO {

    private final JdbcTemplate jdbcTemplate;
    private final UserDAO userDAO;

    public ShopDAO(JdbcTemplate jdbcTemplate, UserDAO userDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDAO = userDAO;
    }

    public boolean addShop(AddShopDTO addShopDTO) {

        boolean status = userDAO.updateRole(new UpdateRole(addShopDTO.owner(), Role.SHOPKEEPER));
        if (status) {
            String addShopSQL = "INSERT INTO `Dustman`.`shop_details` (`activate`, `online`, `create_time`, `shop_address`, `shop_capacity`, `shop_id`, `shop_image_id`, `shop_image_url`, `shop_name`, `user_id`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int result = jdbcTemplate.update(addShopSQL,
                    true,
                    true,
                    new Timestamp(System.currentTimeMillis()),
                    addShopDTO.shopAddress(),
                    addShopDTO.shopCapacity(),
                    UUID.randomUUID().toString(),
                    addShopDTO.shopImageId(),
                    addShopDTO.shopImageUrl(),
                    addShopDTO.shopName(),
                    addShopDTO.owner());
            return result > 0;
        }
        return false;

    }

    public boolean updateShop(AddShopDTO addShopDTO) {
        String sql = "UPDATE `Dustman`.`shop_details` SET `update_time` = ?, `shop_address` = ?, `shop_capacity` = ?, `shop_image_id` = ?, `shop_image_url` = ?, `shop_name` = ? WHERE (`shop_id` = ?);";
        int result = jdbcTemplate.update(sql,
                new Timestamp(System.currentTimeMillis()),
                addShopDTO.shopAddress(),
                addShopDTO.shopCapacity(),
                addShopDTO.shopImageId(),
                addShopDTO.shopImageUrl(),
                addShopDTO.shopName(),
                addShopDTO.owner());
        return result > 0;
    }

    public List<ShopDetails> getAllShop() {
        String sql = "SELECT * FROM Dustman.shop_details";

        List<ShopDetails> shopDetails = jdbcTemplate.query(sql, new ShopRowMapper());
        return shopDetails;
    }

    public String changeOnlineStatus(String id) {
        String sql = "UPDATE `Dustman`.`shop_details` SET `online` = NOT `online` WHERE `shop_id` = ?";
        int result = jdbcTemplate.update(sql, id);
        return result > 0 ? "Status changed successfully" : "Failed to change status";

    }

    public String changeActivateStatus(String id) {
        String sql = "UPDATE `Dustman`.`shop_details` SET `activate` = NOT `activate` WHERE `shop_id` = ?";
        int result = jdbcTemplate.update(sql, id);
        return result > 0 ? "Status changed successfully" : "Failed to change status";

    }

    public List<ShopDetails> activeAndOnlineShop() {
        String sql = "SELECT * FROM Dustman.shop_details WHERE activate = true AND online = true";
        List<ShopDetails> shopDetails = jdbcTemplate.query(sql, new ShopRowMapper());
        return shopDetails;
    }

    public String deleteShop(String id) {
        userDAO.updateRole(new UpdateRole(id, Role.USER));
        String sql = "DELETE FROM `Dustman`.`shop_details` WHERE `shop_id` = ?";
        int result = jdbcTemplate.update(sql, id);
        return result > 0 ? "Shop deleted successfully" : "Failed to delete shop";
    }

}
