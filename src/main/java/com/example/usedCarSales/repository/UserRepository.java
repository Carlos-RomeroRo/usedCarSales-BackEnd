package com.example.usedCarSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usedCarSales.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
