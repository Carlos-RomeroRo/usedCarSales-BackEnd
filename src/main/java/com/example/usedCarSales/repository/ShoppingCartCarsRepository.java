package com.example.usedCarSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usedCarSales.entity.ShoppingCartCars;

public interface ShoppingCartCarsRepository extends JpaRepository<ShoppingCartCars, Long> {
    
}
