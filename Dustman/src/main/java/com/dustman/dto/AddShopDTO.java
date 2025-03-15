package com.dustman.dto;

public record AddShopDTO(
        String shopName,
        String shopAddress,
        String shopCapacity,
        String shopImageUrl,
        String shopImageId,
        String shopId) {}
