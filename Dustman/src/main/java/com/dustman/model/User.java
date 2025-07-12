package com.dustman.model;

import com.dustman.utils.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;

    private String email;

    private Long phoneNo;

    private String password;

    private String address;

    private String userImage;

    private String userImageId;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;


    @OneToMany(mappedBy = "userId")
    private List<Order> orders;

    @OneToMany(mappedBy = "userId")
    private List<Payment> payments;


    public User() {}


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(String userImageId) {
        this.userImageId = userImageId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public User(int userId, String email, Long phoneNo, String password, String address, String userImage, String userImageId, Roles role, Date createdAt, Shop shop, List<Order> orders, List<Payment> payments) {
        this.userId = userId;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.address = address;
        this.userImage = userImage;
        this.userImageId = userImageId;
        this.role = role;
        this.createdAt = createdAt;
        this.shop = shop;
        this.orders = orders;
        this.payments = payments;
    }
}

