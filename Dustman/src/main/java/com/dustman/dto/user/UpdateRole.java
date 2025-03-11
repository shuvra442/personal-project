package com.dustman.dto.user;

import com.dustman.utils.Role;

public class UpdateRole {

    private String userId;
    private Role role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UpdateRole(String userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

    public UpdateRole() {
    }
}
