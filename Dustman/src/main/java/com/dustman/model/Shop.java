package com.dustman.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shopId;

    private String shopEmail;

    private String shopImage;

    private String shopImgId;

    private String shopAdd;

    private boolean onlineStatus;

    private String garbageAmt;

    private String garbagePrice;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @OneToOne
    @JsonIgnoreProperties({ "payments", "orders" ,"shop"})
    private User userId;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopImgId() {
        return shopImgId;
    }

    public void setShopImgId(String shopImgId) {
        this.shopImgId = shopImgId;
    }

    public String getShopAdd() {
        return shopAdd;
    }

    public void setShopAdd(String shopAdd) {
        this.shopAdd = shopAdd;
    }

    public boolean isOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getGarbageAmt() {
        return garbageAmt;
    }

    public void setGarbageAmt(String garbageAmt) {
        this.garbageAmt = garbageAmt;
    }

    public String getGarbagePrice() {
        return garbagePrice;
    }

    public void setGarbagePrice(String garbagePrice) {
        this.garbagePrice = garbagePrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Shop() {
    }

    public Shop(int shopId, String shopEmail, String shopImage, String shopImgId, String shopAdd, boolean onlineStatus, String garbageAmt, String garbagePrice, Date createdAt, User userId) {
        this.shopId = shopId;
        this.shopEmail = shopEmail;
        this.shopImage = shopImage;
        this.shopImgId = shopImgId;
        this.shopAdd = shopAdd;
        this.onlineStatus = onlineStatus;
        this.garbageAmt = garbageAmt;
        this.garbagePrice = garbagePrice;
        this.createdAt = createdAt;
        this.userId = userId;
    }
}
