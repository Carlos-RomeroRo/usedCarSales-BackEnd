package com.example.usedCarSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usedCarSales.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
    
}
