package com.dustman.model;

import com.dustman.utils.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class PaymentDetails {

    @Id
    private String paymentId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp makePaymentTime;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    @JsonIgnore
    private UserDetails user;


    // Add this method to expose only userId
    @JsonGetter("userId")
    public String getUserId() {
        return this.user != null ? this.user.getUserId() : null;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getMakePaymentTime() {
        return makePaymentTime;
    }

    public void setMakePaymentTime(Timestamp makePaymentTime) {
        this.makePaymentTime = makePaymentTime;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public PaymentDetails(PaymentStatus status, Double amount, Timestamp makePaymentTime) {
        this.status = status;
        this.amount = amount;
        this.makePaymentTime = makePaymentTime;
    }

    public PaymentDetails() {
    }
}
