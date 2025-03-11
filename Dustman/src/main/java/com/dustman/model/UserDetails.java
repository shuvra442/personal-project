package com.dustman.model;

import com.dustman.utils.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.List;

@Entity
public class UserDetails {
    @Id
    private String userId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String imageUrl;
    private String imageId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    private String userRoc;   //change TODO
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Timestamp createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Timestamp updatedAt;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ShopDetails shopDetails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PaymentDetails> payments;


    @OneToMany(mappedBy = "orderFrom")
    @JsonIgnore
    private List<OrderDetails> ordersSold;

    @OneToMany(mappedBy = "orderTo")
    @JsonIgnore
    private List<OrderDetails> ordersPurchased;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public List<OrderDetails> getOrdersSold() {
        return ordersSold;
    }

    public void setOrdersSold(List<OrderDetails> ordersSold) {
        this.ordersSold = ordersSold;
    }

    public List<OrderDetails> getOrdersPurchased() {
        return ordersPurchased;
    }

    public void setOrdersPurchased(List<OrderDetails> ordersPurchased) {
        this.ordersPurchased = ordersPurchased;
    }

    public List<PaymentDetails> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDetails> payments) {
        this.payments = payments;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
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

    public ShopDetails getShopDetails() {
        return shopDetails;
    }

    public void setShopDetails(ShopDetails shopDetails) {
        this.shopDetails = shopDetails;
    }

    public UserDetails(String username, String password, String email, String imageUrl, String imageId, Role role, String userRoc, Timestamp createdAt) {
        this.userName = username;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
        this.role = role;
        this.userRoc = userRoc;
        this.createdAt = createdAt;
    }

    public UserDetails() {
    }
}
