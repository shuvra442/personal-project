package com.dustman.utils.rowmapper;

import com.dustman.model.UserDetails;
import com.dustman.utils.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserDetails> {
    @Override
    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDetails userDetails = new UserDetails();

        userDetails.setUserId(rs.getString("user_id"));
        userDetails.setUserName(rs.getString("user_name"));
        userDetails.setEmail(rs.getString("email"));
        userDetails.setPassword(rs.getString("password"));
        userDetails.setImageUrl(rs.getString("image_url"));
        userDetails.setImageId(rs.getString("image_id"));
        String roleString = rs.getString("role");
        if (roleString != null) {
            userDetails.setRole(Role.valueOf(roleString));
        }
        userDetails.setUserRoc(rs.getString("user_roc"));
        userDetails.setCreatedAt(rs.getTimestamp("created_at"));
        userDetails.setUpdatedAt(rs.getTimestamp("updated_at"));


        return userDetails;
    }
}