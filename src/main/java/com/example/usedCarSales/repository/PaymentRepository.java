package com.example.usedCarSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usedCarSales.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
}
