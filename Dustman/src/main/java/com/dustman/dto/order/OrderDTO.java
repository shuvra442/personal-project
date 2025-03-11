package com.dustman.dto.order;

import com.dustman.model.UserDetails;
import com.dustman.utils.OrderStatus;

public class OrderDTO {

    private String orderId;
    private String garbageQuantity;
    private OrderStatus status;
    private String orderFrom;
    private String orderTo;

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


    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getOrderTo() {
        return orderTo;
    }

    public void setOrderTo(String orderTo) {
        this.orderTo = orderTo;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderDTO(String orderId, String garbageQuantity, OrderStatus status, String orderFrom, String orderTo) {
        this.orderId = orderId;
        this.garbageQuantity = garbageQuantity;
        this.status = status;
        this.orderFrom = orderFrom;
        this.orderTo = orderTo;
    }

    public OrderDTO() {
    }
}
