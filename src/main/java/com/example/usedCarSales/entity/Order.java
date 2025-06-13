package com.example.usedCarSales.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import java.time.LocalDate; 
@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Column(nullable = false)
    private LocalDate dateOrder;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
 
    @ManyToOne
    @JoinColumn(name = "carId",  referencedColumnName = "idCar")
    private Car car;

    @OneToOne(mappedBy = "order")
    private Payment payment;
    
}
