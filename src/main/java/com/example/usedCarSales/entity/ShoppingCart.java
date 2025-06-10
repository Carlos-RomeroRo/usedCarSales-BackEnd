package com.example.usedCarSales.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_shopping_cart;

    @OneToOne
    @JoinColumn(name = "user_id",  referencedColumnName = "id_user")
    private User user;

    @OneToMany (mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<ShoppingCartCars> shoppingCartCars;

}
