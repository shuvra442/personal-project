package com.dustman.dto;

import com.dustman.utils.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class UserDTO {

    private String username;
    private String password;
    private String email;
    private String userId;
    private String image;
    private String imageId;
    private String userRoc;
    private String oldPassword;
    private String newPassword;
    private Role role;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedAt;

    public UserDTO() {
    }

    public UserDTO(String userId, String username, String image, String imageId, String userRoc) {
        this.userId = userId;
        this.username = username;
        this.image = image;
        this.imageId = imageId;
        this.userRoc = userRoc;
    }

    public UserDTO(String userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

    public UserDTO(String userId, String username, String password, String email, String image, String imageId, Role role, String userRoc, Timestamp createdAt, Timestamp updatedAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
        this.imageId = imageId;
        this.role = role;
        this.userRoc = userRoc;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUserRoc() {
        return userRoc;
    }

    public void setUserRoc(String userRoc) {
        this.userRoc = userRoc;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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


}
