package com.example.usedCarSales.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.usedCarSales.entity.status.PaymentMethodStatus;

public record PaymentDTO(
    Long idPayment,
    LocalDate datePayment,
    BigDecimal amountPayment,
    PaymentDTO paymentMethod,
    PaymentMethodStatus paymentStatus,
    Long orderId
) {
    
}
