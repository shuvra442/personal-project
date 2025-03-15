package com.dustman.dto;

import com.dustman.utils.OrderStatus;

public record OrderDTO(
        String orderId,
        String garbageQuantity,
        OrderStatus status,
        String orderFrom,
        String orderTo
) {
}
