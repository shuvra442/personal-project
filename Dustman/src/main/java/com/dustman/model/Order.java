package com.dustman.model;

import com.dustman.utils.enums.OrdStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ordId;

    private OrdStatus status;

    private Date ordTime;

    /**
     * payment to user
     */

    @ManyToOne
    private User userId;

}
