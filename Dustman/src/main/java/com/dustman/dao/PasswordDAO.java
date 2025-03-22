package com.dustman.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PasswordDAO {
    private final JdbcTemplate jdbcTemplate;

    public PasswordDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getPassword(String name) {
        String sql = "SELECT password FROM Dustman.password_details WHERE name=?";
        return jdbcTemplate.queryForObject(sql, String.class, name);
    }
}
