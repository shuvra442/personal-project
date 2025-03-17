package com.dustman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PasswordDetails {

    @Id
    private String name;
    @Column(insertable = false)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PasswordDetails() {
    }

    public PasswordDetails(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
