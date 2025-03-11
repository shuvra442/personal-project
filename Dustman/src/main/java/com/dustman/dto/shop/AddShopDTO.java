package com.dustman.dto.shop;

public record AddShopDTO(
        String shopName,
        String shopAddress,
        String shopCapacity,
        String shopImageUrl,
        String shopImageId,
        String owner) {}
