package com.dustman.repository;

import com.dustman.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    List<Payment> findByUserId_UserId(int userId);
}
