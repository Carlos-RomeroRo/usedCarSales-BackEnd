package com.example.usedCarSales.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.usedCarSales.entity.status.PaymentMethodStatus;
import com.example.usedCarSales.entity.status.PaymentStatus;

public record PaymentDTO(
    Long paymentId,
    LocalDate datePayment,
    BigDecimal amountPayment,
    PaymentMethodStatus paymentMethod,
    PaymentStatus paymentStatus,
    Long orderId
) {
    
}
