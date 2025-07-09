package com.dustman.model;

import com.dustman.utils.enums.OrdStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    private String transecId;

    private OrdStatus paymentStatus;

    private Date paymentTime;

}
