package com.dustman.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shopId;

    private String shopEmail;

    private String shopImage;

    private String shopImgId;

    private String shopAdd;

    private boolean onlineStatus;

    private String garbageAmt;

    private String garbagePrice;

    /**
     * connection shop to user
     */

    @OneToOne
    private User userId;

}
