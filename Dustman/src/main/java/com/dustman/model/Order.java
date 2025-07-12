package com.dustman.model;

import com.dustman.utils.enums.OrdStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ordId;

    @Enumerated(EnumType.STRING)
    private OrdStatus status;

    private Date ordTime;

    @ManyToOne
    private User userId;

}
