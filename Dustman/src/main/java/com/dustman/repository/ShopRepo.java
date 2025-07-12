package com.dustman.repository;

import com.dustman.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


    public interface ShopRepo extends JpaRepository<Shop, Integer> {
        Optional<Shop> findByShopEmail(String shopEmail);
    }


