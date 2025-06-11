package com.example.usedCarSales.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


public record OrderDTO(
    Long id, 
    LocalDate dateOrder,
    BigDecimal totalPrice,
    Long userId,
    Long carId,
    Long paymentId) {
}
