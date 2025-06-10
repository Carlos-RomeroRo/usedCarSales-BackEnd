package com.example.usedCarSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usedCarSales.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    
}
