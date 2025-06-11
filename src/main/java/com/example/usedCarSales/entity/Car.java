package com.example.usedCarSales.entity;
import java.math.BigDecimal;
import java.util.List;

import com.example.usedCarSales.entity.status.CarStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false, name="price")
    private BigDecimal price;

    @Column(nullable = false)
    private int mileage;

    @Column(nullable = true)
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CarStatus status;

    //relation with user
    @ManyToOne
    @JoinColumn(name = "userId",  referencedColumnName = "idUser")
    private User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<ShoppingCartCars> shoppingCartCars;

}
