package com.example.usedCarSales.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    
    
        @ManyToOne
        @JoinColumn(name = "idRole")
        private Role roleUser;
    
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    @OneToOne (mappedBy = "user")
    private ShoppingCart shoppingCart;


}
