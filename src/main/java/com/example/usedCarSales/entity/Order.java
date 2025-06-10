package com.example.usedCarSales.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.time.LocalDate; 
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order;

    @Column(nullable = false)
    private LocalDate date_order;

    @Column(nullable = false)
    private BigDecimal total_price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
 
    @ManyToOne
    @JoinColumn(name = "car_id",  referencedColumnName = "id_car")
    private Car car;

    @OneToOne(mappedBy = "order")
    private Payment payment;
    
}
