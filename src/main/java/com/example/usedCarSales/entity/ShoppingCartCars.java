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
    private Long idShoppingCartCar;

    @ManyToOne
    @JoinColumn(name = "shoppingCartId",  referencedColumnName = "idShoppingCart")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "carId",  referencedColumnName = "idCar")
    private Car car;

    // Optional
    @Column(nullable = true)
    private int quantity;
    
}
