package com.dustman.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class ShopDetails {

    @Id
    private String shopId;
    @Column(nullable = false)
    private String shopName;
    @Column(nullable = false)
    private String shopAddress;
    @Column(nullable = false)
    private String shopCapacity;
    private String shopImageUrl;
    private String shopImageId;
    @Column(nullable = false)
    private boolean online;
    @Column(nullable = false)
    private boolean activate;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Timestamp createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Timestamp updateTime;
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @JsonIgnore
    private UserDetails owner;


    @JsonGetter("userId")
    public String getUserId() {
        return this.owner != null ? this.owner.getUserId() : null;
    }

    public String getShopImageId() {
        return shopImageId;
    }

    public void setShopImageId(String shopImageId) {
        this.shopImageId = shopImageId;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopCapacity() {
        return shopCapacity;
    }

    public void setShopCapacity(String shopCapacity) {
        this.shopCapacity = shopCapacity;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getShopImageUrl() {
        return shopImageUrl;
    }

    public void setShopImageUrl(String shopImageUrl) {
        this.shopImageUrl = shopImageUrl;
    }

    public UserDetails getOwner() {
        return owner;
    }

    public void setOwner(UserDetails owner) {
        this.owner = owner;
    }

    public ShopDetails(String shopName, String shopAddress, String shopCapacity, Timestamp createTime, Timestamp updateTime, String shopImageUrl,String shopImageId, boolean online, boolean activate) {

        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopCapacity = shopCapacity;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shopImageId = shopImageId;
        this.shopImageUrl = shopImageUrl;
        this.online = online;
        this.activate = activate;
    }

    public ShopDetails() {
    }
}
