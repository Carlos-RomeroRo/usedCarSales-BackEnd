package com.example.usedCarSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usedCarSales.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
