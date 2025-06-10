package com.example.usedCarSales.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart_cars")
public class ShoppingCartCars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_shopping_cart_car;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id",  referencedColumnName = "id_shopping_cart")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "car_id",  referencedColumnName = "id_car")
    private Car car;

    // Optional
    @Column(nullable = true)
    private int quantity;
    
}
