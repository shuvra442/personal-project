package com.dustman.model;

import com.dustman.utils.OrderStatus;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class OrderDetails {

    @Id
    private String orderId;
    @Column(nullable = false)
    private String garbageQuantity;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "order_from_user_id")
    @JsonIgnore
    private UserDetails orderFrom;

    @ManyToOne
    @JoinColumn(name = "order_to_user_id")
    @JsonIgnore
    private UserDetails orderTo;

    @JsonGetter("orderFromUserId")
    public String getOrderFromUserId() {
        return this.orderFrom != null ? this.orderFrom.getUserId() : null;
    }
    @JsonGetter("orderToUserId")
    public String getOrderToUserId() {
        return this.orderTo != null ? this.orderTo.getUserId() : null;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGarbageQuantity() {
        return garbageQuantity;
    }

    public void setGarbageQuantity(String garbageQuantity) {
        this.garbageQuantity = garbageQuantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserDetails getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(UserDetails orderFrom) {
        this.orderFrom = orderFrom;
    }

    public UserDetails getOrderTo() {
        return orderTo;
    }

    public void setOrderTo(UserDetails orderTo) {
        this.orderTo = orderTo;
    }

    public OrderDetails(String garbageQuantity, OrderStatus status, UserDetails orderFrom, UserDetails orderTo) {
        this.garbageQuantity = garbageQuantity;
        this.status = status;
        this.orderFrom = orderFrom;
        this.orderTo = orderTo;
    }

    public OrderDetails() {
    }
}
