package com.dustman.dto.user;

import com.dustman.utils.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class UserInfo {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String imageUrl;
    private String imageId;
    private Role role;
    private String userRoc;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserRoc() {
        return userRoc;
    }

    public void setUserRoc(String userRoc) {
        this.userRoc = userRoc;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserInfo() {
    }

    public UserInfo(String userId, String userName, String password, String email, String imageUrl, String imageId, Role role, String userRoc, Timestamp createdAt, Timestamp updatedAt) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
        this.role = role;
        this.userRoc = userRoc;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
