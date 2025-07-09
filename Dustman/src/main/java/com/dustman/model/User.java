package com.dustman.model;

import com.dustman.utils.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String email;

    private Long phoneNo;

    private String password;

    private String address;

    private String userImage;

    private String userImageId;

    private Roles role;

    private Date createdAt;

    /**
     * user to shop
     */
    @OneToOne
    private Shop shopId;

}
