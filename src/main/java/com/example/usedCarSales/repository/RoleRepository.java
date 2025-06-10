package com.example.usedCarSales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usedCarSales.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
