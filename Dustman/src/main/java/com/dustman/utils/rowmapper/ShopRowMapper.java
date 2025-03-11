package com.dustman.utils.rowmapper;

import com.dustman.model.ShopDetails;
import com.dustman.model.UserDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopRowMapper implements RowMapper<ShopDetails> {

    @Override
    public ShopDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShopDetails shopDetails = new ShopDetails();
        shopDetails.setShopId(rs.getString("shop_id"));
        shopDetails.setShopName(rs.getString("shop_name"));
        shopDetails.setShopAddress(rs.getString("shop_address"));
        shopDetails.setShopCapacity(rs.getString("shop_capacity"));
        shopDetails.setShopImageUrl(rs.getString("shop_image_url"));
        shopDetails.setShopImageId(rs.getString("shop_image_id"));
        shopDetails.setOnline(rs.getBoolean("online"));
        shopDetails.setActivate(rs.getBoolean("activate"));
        shopDetails.setCreateTime(rs.getTimestamp("create_time"));
        shopDetails.setUpdateTime(rs.getTimestamp("update_time"));
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(rs.getString("user_id"));
        shopDetails.setOwner(userDetails);
        return shopDetails;
    }
}
