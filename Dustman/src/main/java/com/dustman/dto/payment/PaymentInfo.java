package com.dustman.dto.payment;

import com.dustman.utils.PaymentStatus;

import java.sql.Timestamp;

public record PaymentInfo(String paymentId, PaymentStatus status, Double amount, Timestamp createTime, String user) {}
