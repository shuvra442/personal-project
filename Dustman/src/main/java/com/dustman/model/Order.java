package com.dustman.model;

import com.dustman.utils.enums.OrdStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ordId;

    @Enumerated(EnumType.STRING)
    private OrdStatus status;

    private Date ordTime;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @ManyToOne
    @JsonIgnoreProperties({ "payments", "orders" ,"shop"})
    private User userId;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonIgnoreProperties("user_id")
    private Shop shop ;


    public Order() {
    }

    public Order(int ordId, OrdStatus status, Date ordTime, Date createdAt, User userId, Shop shop) {
        this.ordId = ordId;
        this.status = status;
        this.ordTime = ordTime;
        this.createdAt = createdAt;
        this.userId = userId;
        this.shop = shop;
    }

    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    public OrdStatus getStatus() {
        return status;
    }

    public void setStatus(OrdStatus status) {
        this.status = status;
    }

    public Date getOrdTime() {
        return ordTime;
    }

    public void setOrdTime(Date ordTime) {
        this.ordTime = ordTime;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
