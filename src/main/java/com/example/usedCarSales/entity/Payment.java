package com.example.usedCarSales.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.usedCarSales.entity.status.PaymentMethodStatus;
import com.example.usedCarSales.entity.status.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long idPayment;

    @Column(nullable = false)
    private LocalDate datePayment;

    @Column(nullable = false)
    private BigDecimal amountPayment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodStatus paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @OneToOne
    @JoinColumn(name = "orderId")
    private Order order;

}
