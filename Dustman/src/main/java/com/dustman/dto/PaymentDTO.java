package com.dustman.dto;

public class PaymentDTO {
    private String orderId;
    private String amount;
    private String items;
    private String paymentStatus;
    private String userID;
    private String paymentId;
    private String razorpaySignature;

    public PaymentDTO(String userID,String paymentStatus,String orderId, String amount, String items) {
        this.orderId = orderId;
        this.amount = amount;
        this.items = items;
        this.paymentStatus = paymentStatus;
        this.userID = userID;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentDTO() {
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "orderId='" + orderId + '\'' +
                ", amount='" + amount + '\'' +
                ", items='" + items + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
