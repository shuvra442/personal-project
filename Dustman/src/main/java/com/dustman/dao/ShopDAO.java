package com.dustman.dao;


import com.dustman.dto.AddShopDTO;
import com.dustman.dto.UserDTO;
import com.dustman.model.ShopDetails;
import com.dustman.utils.Role;
import com.dustman.utils.rowmapper.ShopRowMapper;
import jakarta.transaction.Transactional;
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

        boolean status = userDAO.updateRole(new UserDTO(addShopDTO.shopId(), Role.SHOPKEEPER));
        if (status) {
            String addShopSQL = "INSERT INTO `Dustman`.`shop_details` (`activate`, `online`, `create_time`, `shop_address`, `shop_capacity`, `shop_id`, `shop_image_id`, `shop_image_url`, `shop_name`, `user_id`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int result = jdbcTemplate.update(addShopSQL, true, true, new Timestamp(System.currentTimeMillis()), addShopDTO.shopAddress(), addShopDTO.shopCapacity(), UUID.randomUUID().toString(), addShopDTO.shopImageId(), addShopDTO.shopImageUrl(), addShopDTO.shopName(), addShopDTO.shopId());
            return result > 0;
        }
        return false;

    }

    public boolean updateShop(AddShopDTO addShopDTO) {
        String sql = "UPDATE `Dustman`.`shop_details` SET `update_time` = ?, `shop_address` = ?, `shop_capacity` = ?, `shop_image_id` = ?, `shop_image_url` = ?, `shop_name` = ? WHERE (`shop_id` = ?);";
        int result = jdbcTemplate.update(sql, new Timestamp(System.currentTimeMillis()), addShopDTO.shopAddress(), addShopDTO.shopCapacity(), addShopDTO.shopImageId(), addShopDTO.shopImageUrl(), addShopDTO.shopName(), addShopDTO.shopId());
        return result > 0;
    }

    public List<ShopDetails> getAllShop() {
        String sql = "SELECT * FROM Dustman.shop_details";
        return jdbcTemplate.query(sql, new ShopRowMapper());
    }

    public boolean changeOnlineStatus(String id) {
        String sql = "UPDATE `Dustman`.`shop_details` SET `online` = NOT `online` WHERE `shop_id` = ?";
        int result = jdbcTemplate.update(sql, id);
        return result > 0 ;

    }

    public Boolean changeActivateStatus(String id) {
        String sql = "UPDATE `Dustman`.`shop_details` SET `activate` = NOT `activate` WHERE `shop_id` = ?";
        int result = jdbcTemplate.update(sql, id);
        return result > 0 ;

    }

    public List<ShopDetails> activeAndOnlineShop() {
        String sql = "SELECT * FROM Dustman.shop_details WHERE activate = true AND online = true";
        List<ShopDetails> shopDetails = jdbcTemplate.query(sql, new ShopRowMapper());
        return shopDetails;
    }

    @Transactional
    public Boolean deleteShop(String id) {


        String userIDSQL = "SELECT `user_id` FROM `Dustman`.`shop_details` WHERE `shop_id`= ?";
        String userId = jdbcTemplate.queryForObject(userIDSQL, String.class, id);
        userDAO.updateRole(new UserDTO(userId, Role.USER));
        String sql = "DELETE FROM `Dustman`.`shop_details` WHERE `shop_id` = ?";
        int result = jdbcTemplate.update(sql, id);
        return result > 0 ;
    }

    public String getEmailID(String id){
        String sql="SELECT u.email FROM shop_details s JOIN user_details u ON s.user_id = u.user_id WHERE s.shop_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class,id);
    }


}
