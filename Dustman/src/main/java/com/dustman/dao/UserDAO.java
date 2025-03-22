package com.dustman.dao;

import com.dustman.dto.UserDTO;
import com.dustman.model.UserDetails;
import com.dustman.utils.Role;
import com.dustman.utils.rowmapper.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserDAO {

    public final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public String registerUser(UserDTO registerInfo) {
        if (checkEmail(registerInfo.getEmail())) {
            return "exist";
        }
        String sql = "INSERT INTO `Dustman`.`user_details` (`created_at`,`email`, `password`, `role`, `user_id`, `user_name`) VALUES (?,?,?,?,?,?)";
        int reasult = jdbcTemplate.update(sql, new Timestamp(System.currentTimeMillis()), registerInfo.getEmail(), registerInfo.getPassword(), Role.USER.toString(), UUID.randomUUID().toString(), registerInfo.getUsername());
        return (reasult > 0) ? "true" : "false";

    }

    public boolean updateUser(UserDTO updatedUserInfo) {
        String sql = "UPDATE `Dustman`.`user_details` SET `user_name` = ?, `image_url` = ?, `image_id` = ?, `user_roc` = ?,`updated_at` = ? WHERE `user_id` = ?";
        int status = jdbcTemplate.update(sql, updatedUserInfo.getUsername(), updatedUserInfo.getImage(), updatedUserInfo.getImageId(), updatedUserInfo.getUserRoc(), new Timestamp(System.currentTimeMillis()), updatedUserInfo.getUserId());
        return status > 0;
    }

    public boolean checkUser(String id) {
        String sql = "SELECT COUNT(*) FROM Dustman.user_details WHERE user_id = ?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return result > 0;

    }

    public UserDetails getUserById(String id) {
        String sql = "SELECT * FROM Dustman.user_details WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    public List<UserDetails> getAllUser() {
        String sql = "SELECT * FROM Dustman.user_details";

        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public boolean checkEmail(String email) {
        String checkEmailQuery = "SELECT COUNT(*) FROM Dustman.user_details WHERE email = ?";
        int count = jdbcTemplate.queryForObject(checkEmailQuery, new Object[]{email}, Integer.class);
        return count > 0;

    }

    public boolean updatePassword(String userId, String newPassword) {
        String sql = "UPDATE `Dustman`.`user_details` SET `password` = ?,`updated_at` = ? WHERE `user_id` = ?";
        int status = jdbcTemplate.update(sql, newPassword, new Timestamp(System.currentTimeMillis()), userId);
        return status > 0;
    }

    public boolean checkPassword(String userId, String password) {
        String retrievePassword = "SELECT password FROM `Dustman`.`user_details` WHERE `user_id` = ?";
        String oldPassword = jdbcTemplate.queryForObject(retrievePassword, String.class, userId);
        return Objects.equals(oldPassword, password);
    }

    public boolean updateRole(UserDTO updateRole) {
        try {
            int status;
            String sql = "UPDATE `Dustman`.`user_details` SET `role` = ?,`updated_at` = ? WHERE `user_id` = ?";

            status = jdbcTemplate.update(sql, updateRole.getRole().toString(), new Timestamp(System.currentTimeMillis()), updateRole.getUserId());
            return status > 0;
        } catch (DataAccessException e) {
            return false;
        }

    }

    public boolean deleteUser(String id) {
        String firstSQL = "SELECT `role` FROM `Dustman`.`user_details` WHERE `user_id`= ?";
        String role = jdbcTemplate.queryForObject(firstSQL, String.class, id);
        if (Objects.equals(role, "SHOPKEEPER")) {
            String nullifyOrdersSql = "UPDATE Dustman.order_details SET  order_to_user_id = NULL WHERE order_to_user_id = ?";
            jdbcTemplate.update(nullifyOrdersSql, id);

        } else if (Objects.equals(role, "USER")) {
            String nullifyOrdersSql = "UPDATE Dustman.order_details SET order_from_user_id = NULL WHERE order_from_user_id = ? ";
            jdbcTemplate.update(nullifyOrdersSql, id);
        }

        String sql = "DELETE FROM `Dustman`.`user_details` WHERE `user_id` = ?";
        int status = jdbcTemplate.update(sql, id);
        return status > 0;
    }

    public String getEmailID(String id) {
        String sql = "SELECT email FROM user_details WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    public List<String> retrieveSellerIds(String id) {
        String sql = "SELECT order_from_user_id FROM order_details WHERE order_to_user_id = ?";
        return jdbcTemplate.queryForList(sql, String.class, id);
    }

    public List<String> retrieveBuyerIds(String id) {
        String sql = "SELECT order_to_user_id FROM order_details WHERE order_from_user_id = ?";
        return jdbcTemplate.queryForList(sql, String.class, id);
    }

    public String getUserName(String id) {
        String sql="SELECT user_name FROM user_details WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql,String.class,id);
    }
}
