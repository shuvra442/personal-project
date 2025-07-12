package com.dustman.model;

import com.dustman.utils.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String email;

    private Long phoneNo;

    private String password;

    private String address;

    private String userImage;

    private String userImageId;

    @Enumerated(EnumType.STRING)
    private Roles role;

    private Date createdAt;

    /**
     * user to shop
     */
    @OneToOne
    private Shop shopId;


    @OneToMany(mappedBy = "userId")
    private List<Order> orders;

    @OneToMany(mappedBy = "userId")
    private List<Payment> payments;


}

